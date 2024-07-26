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
@Table(name = "products", schema = "dbo", indexes = {
        @Index(name = "idx_products_name", columnList = "product_name")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @Nationalized
    @Lob
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "category_id")
    private Integer categoryId;

    @Size(max = 50)
    @Nationalized
    @ColumnDefault("N'available'")
    @Column(name = "status", length = 50)
    private String status;

    @ColumnDefault("getdate()")
    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "productId")
    private Set<Cart> carts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "productId")
    private Set<OrderItem> orderItems = new LinkedHashSet<>();

    @OneToMany(mappedBy = "productId")
    private Set<Review> reviews = new LinkedHashSet<>();

}