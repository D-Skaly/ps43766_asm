package skaly.ps43766_asm.dto;

import io.jsonwebtoken.Claims;
import skaly.ps43766_asm.entity.Role;
import skaly.ps43766_asm.utils.JwtUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSession {
    private String username;
    private List<Role> roles;
}
