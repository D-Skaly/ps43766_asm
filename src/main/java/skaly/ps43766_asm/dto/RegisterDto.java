package skaly.ps43766_asm.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import skaly.ps43766_asm.entity.*;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link User}
 */
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
public class RegisterDto implements Serializable {
    @NotNull
    @Size(max = 50)
    private final String username;
    @NotNull
    @Size(max = 255)
    private final String password;
    @NotNull
    @Size(max = 100)
    private final String email;
    @Size(max = 100)
    private final String fullName;
    @Size(max = 255)
    private final String address;
    @Size(max = 20)
    private final String phone;
    private final Set<Cart> carts;
    private final Set<Order> orders;
    private final Set<Review> reviews;
    private final Set<UserRole> userRoles;

    public boolean isPasswordMatching() {
        return false;
    }
}