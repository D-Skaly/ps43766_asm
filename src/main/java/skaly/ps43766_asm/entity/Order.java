package skaly.ps43766_asm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders", schema = "dbo", indexes = {
        @Index(name = "idx_orders_user_id", columnList = "user_id")
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @NotNull
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Size(max = 50)
    @Nationalized
    @ColumnDefault("N'pending'")
    @Column(name = "status", length = 50)
    private String status;

    @ColumnDefault("getdate()")
    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "orderId")
    private Set<OrderItem> orderItems = new LinkedHashSet<>();

}