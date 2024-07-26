package skaly.ps43766_asm.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import skaly.ps43766_asm.entity.Role;
import skaly.ps43766_asm.entity.RoleType;
import skaly.ps43766_asm.entity.UserRole;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtUtil {

    private static final String SECRET_KEY = "your-secret-key";

    /**
     * Validate the JWT token.
     * @param token the JWT token
     * @return true if the token is valid, false otherwise
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extract claims from the given JWT token.
     * @param token the JWT token
     * @return the claims from the token
     */
    public static Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Generate a JWT token with the given username and roles.
     * @param username the username to include in the token
     * @param roles the list of roles to include in the token
     * @return the generated JWT token
     */
    public static String generateToken(String username, Set<UserRole> roles) {
        return Jwts.builder()
                .claim("username", username)
                .claim("roles", roles.stream()
                        .map(UserRole::getRoleId)
                        .collect(Collectors.toList()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * Extract roles from the given JWT token.
     * @param token the JWT token
     * @return a list of roles
     */
    public static List<Role> getRoleFromToken(String token) {
        Claims claims = getClaimsFromToken(token);

        // Get roles from claims and ensure it is a list
        Object rolesObj = claims.get("roles");
        if (rolesObj instanceof List<?>) {
            List<?> roleNamesList = (List<?>) rolesObj;

            // Check if each element in the list is a string
            if (roleNamesList.stream().allMatch(item -> item instanceof String)) {
                @SuppressWarnings("unchecked")
                List<String> roleNames = (List<String>) roleNamesList;

                // Convert role names to Role objects
                return roleNames.stream()
                        .map(roleName -> {
                            try {
                                return Role.builder().roleName(roleName).build();
                            } catch (IllegalArgumentException e) {
                                return null; // or handle error
                            }
                        })
                        .filter(Objects::nonNull) // Remove any null roles
                        .collect(Collectors.toList());
            } else {
                throw new IllegalArgumentException("Invalid role data in token");
            }
        } else {
            throw new IllegalArgumentException("Roles claim is not a list");
        }
    }

    /**
     * Extract username from the given JWT token.
     * @param token the JWT token
     * @return the username from the token
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("username", String.class);
    }

    /**
     * Extract token from request header.
     * @param request the HttpServletRequest
     * @return the token string or null if not found
     */
    public static String getTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
