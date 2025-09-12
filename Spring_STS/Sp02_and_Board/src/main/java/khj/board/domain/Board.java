package khj.board.domain;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board {
	private long seq;
	private String writer;
	private String email;
	private String subject;
	private String content;
	private Date rdate;
	// private MultipartFile file;
}
