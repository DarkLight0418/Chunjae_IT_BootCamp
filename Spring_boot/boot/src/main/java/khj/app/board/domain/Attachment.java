package khj.app.board.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attachment {
	private long id;
	private long boardSeq;
	private String fname;
	private String ofname;
	private long fsize;
	private String contentType;
	private Date uploadDate;
}
