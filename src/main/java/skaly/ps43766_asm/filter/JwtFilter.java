package skaly.ps43766_asm.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Claims;
import skaly.ps43766_asm.utils.JwtUtil;

import java.io.IOException;

@WebFilter(filterName = "jwtFilter", value = "/admin/*")
public class JwtFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = extractToken(httpRequest);
        if (token == null) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

        try {
            Claims claims = JwtUtil.getClaimsFromToken(token);
            // Optionally set claims or user info in the request attributes if needed
            httpRequest.setAttribute("claims", claims);
        } catch (Exception e) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        chain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        return JwtUtil.getTokenFromRequest(request);
    }

    @Override
    public void destroy() {
    }
}
