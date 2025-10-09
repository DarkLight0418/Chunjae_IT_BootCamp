package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payment")
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(name = "original_price")
    private Long originalPrice;

    @Column(name = "paid_price")
    private Long paidPrice;

    @Column(name = "discount_price")
    private Long discountPrice;
}
