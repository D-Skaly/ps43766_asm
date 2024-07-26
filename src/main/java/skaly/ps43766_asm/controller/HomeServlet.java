package skaly.ps43766_asm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import skaly.ps43766_asm.entity.Role;
import skaly.ps43766_asm.entity.RoleType;
import skaly.ps43766_asm.utils.JwtUtil;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "homeServlet", value = {"/", "/home"}, loadOnStartup = 1)
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract JWT token from the request
        String token = JwtUtil.getTokenFromRequest(request);

        if (token != null && JwtUtil.validateToken(token)) {
            try {
                // Parse token to get user details
                String username = JwtUtil.getUsernameFromToken(token);
                List<Role> roles = JwtUtil.getRoleFromToken(token);

                // Set greeting message
                request.setAttribute("greetingMessage", "Welcome, " + username + "!");

                // Determine the content page based on user role
                String pageTitle = "Home";
                for (Role role : roles) {
                    if (role.getRoleName().equalsIgnoreCase(RoleType.ADMIN.name())) {
                        request.setAttribute("contentPage", "views/admin/adminHome.jsp");
                        pageTitle = "Admin Home";
                    } else if (role.getRoleName().equalsIgnoreCase(RoleType.USER.name())) {
                        request.setAttribute("contentPage", "views/customer/customerHome.jsp");
                        pageTitle = "Customer Home";
                    } else if (role.getRoleName().equalsIgnoreCase(RoleType.MANAGER.name())) {
                        request.setAttribute("contentPage", "views/manager/managerHome.jsp");
                        pageTitle = "Manager Home";
                    }
                }

                request.setAttribute("pageTitle", pageTitle);
            } catch (Exception e) {
                // Handle token parsing errors or invalid tokens
                request.setAttribute("errorMessage", "Invalid or expired token.");
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
        } else {
            request.setAttribute("greetingMessage", "Welcome to Our Online Shop!");
            request.setAttribute("contentPage", "/views/home/homePage.jsp");
            request.setAttribute("pageTitle", "Home");
        }

        request.getRequestDispatcher("views/common/layout.jsp").forward(request, response);
    }
}
