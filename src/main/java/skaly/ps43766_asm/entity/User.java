package skaly.ps43766_asm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users", schema = "dbo", indexes = {
        @Index(name = "idx_users_username", columnList = "username"),
        @Index(name = "idx_users_email", columnList = "email")
}, uniqueConstraints = {
        @UniqueConstraint(name = "UQ__users__F3DBC57208A30512", columnNames = {"username"}),
        @UniqueConstraint(name = "UQ__users__AB6E616491FE0823", columnNames = {"email"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 100)
    @Nationalized
    @Column(name = "full_name", length = 100)
    private String fullName;

    @Size(max = 255)
    @Nationalized
    @Column(name = "address")
    private String address;

    @Size(max = 20)
    @Nationalized
    @Column(name = "phone", length = 20)
    private String phone;

    @ColumnDefault("getdate()")
    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "userId")
    private Set<Cart> carts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userId")
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userId")
    private Set<Review> reviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userId")
    private Set<UserRole> userRoles = new LinkedHashSet<>();

}