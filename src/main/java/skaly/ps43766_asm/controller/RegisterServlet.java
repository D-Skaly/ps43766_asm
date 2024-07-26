package skaly.ps43766_asm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import skaly.ps43766_asm.dto.RegisterDto;
import skaly.ps43766_asm.entity.User;
import skaly.ps43766_asm.service.UserService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/common/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleSignUp(request, response);
    }

    private void handleSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();

        if ("POST".equalsIgnoreCase(method)) {
            RegisterDto registerDto = new RegisterDto();
            try {
                BeanUtils.populate(registerDto, request.getParameterMap());

                Map<String, String> errorMessages = validateSignUp(registerDto);

                // Check for existing username
                if (userService.findByUsername(registerDto.getUsername()) != null) {
                    errorMessages.put("username", "Username is already taken");
                }

                // Check if passwords match
                if (!registerDto.isPasswordMatching()) {
                    errorMessages.put("confirmPassword", "Passwords do not match");
                }

                if (!errorMessages.isEmpty()) {
                    forwardToSignUpPage(request, response, errorMessages);
                    return;
                }
                String hashedPassword = hashPassword(registerDto.getPassword());

                User newUser = User.builder()
                        .username(registerDto.getUsername())
                        .fullName(registerDto.getFullName())
                        .email(registerDto.getEmail())
                        .phone(registerDto.getPhone())
                        .userRoles(registerDto.getUserRoles())
                        .password(hashedPassword)
                        .build();

                userService.save(newUser);

                HttpSession session = request.getSession();
                session.setAttribute("signUpMessage", "Successfully signed up.");
                response.sendRedirect(request.getContextPath() + "/login");

            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new ServletException("Error processing registration data", e);
            }
        }
    }

    private Map<String, String> validateSignUp(RegisterDto signUpDTO) {
        Set<ConstraintViolation<RegisterDto>> violations = validator.validate(signUpDTO);
        Map<String, String> errorMessages = new HashMap<>();
        for (ConstraintViolation<RegisterDto> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errorMessages.put(fieldName, message);
        }
        return errorMessages;
    }

    private void forwardToSignUpPage(HttpServletRequest request, HttpServletResponse response, Map<String, String> errorMessages) throws ServletException, IOException {
        request.setAttribute("errorMessages", errorMessages);
        request.getRequestDispatcher("views/common/register.jsp").forward(request, response);
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
