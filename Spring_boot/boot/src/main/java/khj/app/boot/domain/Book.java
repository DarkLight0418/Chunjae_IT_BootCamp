package khj.app.boot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    //@ManyToOne(fetch = FetchType.EAGER) //즉시로딩(비추)
    @ManyToOne(fetch = FetchType.LAZY) //지연로딩: 서비스 구현클래스에 @Transactional 해 줘야 함
    @JoinColumn(name="author_id")
    @ToString.Exclude
    private Author author; //getAuthor()가 호출될 때만 채움
}
