package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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
}
