package skaly.ps43766_asm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import skaly.ps43766_asm.dto.LoginDTO;
import skaly.ps43766_asm.entity.Role;
import skaly.ps43766_asm.entity.User;
import skaly.ps43766_asm.entity.UserRole;
import skaly.ps43766_asm.service.UserService;
import skaly.ps43766_asm.utils.JwtUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/common/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleSignIn(request, response);
    }

    private void handleSignIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();

        if ("POST".equalsIgnoreCase(method)) {
            LoginDTO loginDTO = new LoginDTO();
            try {
                BeanUtils.populate(loginDTO, request.getParameterMap());
                List<User> users = userService.findByUsername(loginDTO.getUsername());

                if (users.isEmpty()) {
                    // Xử lý khi không tìm thấy người dùng
                    request.setAttribute("errorMessage", "Invalid username");
                    request.getRequestDispatcher("/views/common/login.jsp").forward(request, response);
                    return;
                }

                User user = users.get(0);
                if (BCrypt.checkpw(loginDTO.getPassword().trim(), user.getPassword().trim())) {
                    Set<UserRole> roles = user.getUserRoles();
                    String token = JwtUtil.generateToken(loginDTO.getUsername(), roles);
                    HttpSession session = request.getSession();
                    session.setAttribute("jwtToken", token);
                    response.sendRedirect(request.getContextPath() + "/home");
                } else {
                    request.setAttribute("errorMessage", "Invalid password");
                    request.getRequestDispatcher("/views/common/login.jsp").forward(request, response);
                }
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new ServletException("Error processing user data", e);
            }
        } else {
            if (getLoggedInUser(request) != null) {
                request.getSession().setAttribute("loginMessage", "You are already logged in.");
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                request.getRequestDispatcher("/views/common/login.jsp").forward(request, response);
            }
        }
    }


    private User getLoggedInUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("jwtToken");
        if (token != null) {
            String username = JwtUtil.getUsernameFromToken(token);
            List<User> users = userService.findByUsername(username);

            if (!users.isEmpty()) {
                return users.get(0);
            }
        }
        return null;
    }

}
