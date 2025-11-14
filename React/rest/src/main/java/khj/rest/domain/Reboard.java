package khj.rest.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "reboard")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(length = 20)
    private String name;
    @Column(length = 40)
    private String subject;
    @Column(length = 50)
    private String content;

    /*
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    @Column(insertable = true, updatable = false)
    private java.time.LocalDateTime regdate;
     */

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(insertable = false, updatable = true)
    private java.time.LocalDateTime regdate;
}
