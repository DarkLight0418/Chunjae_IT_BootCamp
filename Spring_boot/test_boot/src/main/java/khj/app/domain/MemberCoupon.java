package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "member_coupon")
@NoArgsConstructor
public class MemberCoupon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_coupon_id")
    private Long memberCouponId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "issued_at")
    @CreationTimestamp
    private LocalDateTime issuedAt;

    @Column(name = "used_at")
    @CreationTimestamp
    private LocalDateTime usedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "coupon_state")
    private CouponState couponState;

    private enum CouponState {
        ISSUED, USED
    }
}
