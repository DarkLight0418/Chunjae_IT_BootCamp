package khj.app.board.domain;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본키 생성을 DB에게 위임(MySQL - 오토 그거)
	private long id;

	private String fname;
	private String ofname;
	private long fsize;
	private String contentType;
	private Date uploadDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_seq")
    private Board board;
}
