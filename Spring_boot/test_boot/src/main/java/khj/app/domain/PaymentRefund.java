package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payment_refund")
@NoArgsConstructor
public class PaymentRefund {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_refund_id")
    private Long orderRefundId;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "payment_detail_id")
    private PaymentDetails paymentDetails;

    @Column(name = "refund_reason", columnDefinition = "TEXT")
    private String refundReason;

    @Column(name = "refund_price")
    private Long refundPrice;

    @Column(name = "is_refunded", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isRefunded;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
