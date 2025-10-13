package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Coupon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long couponId;

    @Column(length = 100)
    private String name;

    @Column(name = "discount_price")
    private Long discountPrice;

    @Column(name = "discount_rate")
    private Long discountRate;

    @Column(name = "valid_from")
    private LocalDateTime validFrom;

    @Column(name = "vaild_until")
    private LocalDateTime validUntil;

    @Column(name = "available_scope", length = 100)
    private String availableScope;

    @Column(name = "discount_limit")
    private Long discountLimit;
}
