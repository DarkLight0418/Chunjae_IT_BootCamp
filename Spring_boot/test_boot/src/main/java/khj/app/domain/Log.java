package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Log {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    @Column(name = "action_type", length = 100)
    private String actionType;

    @Column(name = "target_entity", length = 50)
    private String targetEntity;

    @Column(name = "target_id", columnDefinition = "BIGINT UNSIGNED")
    private Long targetId;

    @Column(name = "action_at")
    @CreationTimestamp
    private LocalDateTime actionAt;

    @Column(name = "is_success", columnDefinition = "TINYINT(1)")
    private boolean isSuccess;
}
