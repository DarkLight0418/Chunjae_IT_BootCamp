package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payment_refund")
public class PaymentRefund {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_refund_id")
    private Long orderRefundId;

    @OneToOne
    private Payment payment;

    @OneToOne
    private PaymentDetails paymentDetails;

    @Column(name = "refund_reason", columnDefinition = "TEXT")
    private String refundReason;

    @Column(name = "refund_price")
    private Long refundPrice;

    @Column(name = "is_refunded", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isRefunded;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
