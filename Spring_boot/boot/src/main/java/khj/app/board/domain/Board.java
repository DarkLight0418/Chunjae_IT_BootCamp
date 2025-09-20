package khj.app.board.domain;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Board {
    @Id
	private long seq;
	private String writer;
	private String email;
	private String subject;
	private String content;
	private Date rdate;
	// private MultipartFile file;
}
