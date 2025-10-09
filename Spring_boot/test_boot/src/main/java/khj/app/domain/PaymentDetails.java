package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "payment_details")
public class PaymentDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_detail_id")
    private Long paymentDatailId;

    @OneToOne
    private Payment payment;

    @Column(name = "payment_api_result", columnDefinition = "TEXT")
    private Long paymentApiResult;
}
