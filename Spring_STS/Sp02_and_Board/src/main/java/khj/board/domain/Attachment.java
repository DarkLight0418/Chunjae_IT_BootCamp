package khj.board.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Attachment {
	private Long id;
	private Long boardSeq;
	private String fname;
	private String ofname;
	private Long fsize;
	private String contentType;
	private Date uploadDate;
}
