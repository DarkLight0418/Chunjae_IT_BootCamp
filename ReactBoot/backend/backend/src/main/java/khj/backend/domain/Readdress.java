package khj.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Table(name = "readdress")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Readdress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10)
    private String zip;
    @Column(length = 255)
    private String addr;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    @Column(insertable = true, updatable = false)
    private java.time.LocalDateTime rdate;

    @LastModifiedDate  // 자동으로 시간 업데이트
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(insertable = false, updatable = true)
    private java.time.LocalDateTime udate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reuser_id")
    private Reuser reuser;
}