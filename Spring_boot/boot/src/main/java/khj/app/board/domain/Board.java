package khj.app.board.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본키 생성을 DB에게 위임(MySQL - 오토 그거)
	private long seq;
	private String writer;
	private String email;
	private String subject;
	private String content;

    @CreationTimestamp
    @Column(updatable = true)
	private Date rdate;
	// private MultipartFile file;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments = new ArrayList<>();
}
