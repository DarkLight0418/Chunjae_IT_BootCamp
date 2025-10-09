package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "member_coupon")
public class MemberCoupon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_coupon_id")
    private Long memberCouponId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(name = "issued_at")
    private LocalDateTime issuedAt;

    @Column(name = "used_at")
    private LocalDateTime usedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "coupon_state")
    private CouponState couponState;

    private enum CouponState {
        ISSUED, USED
    }
}
