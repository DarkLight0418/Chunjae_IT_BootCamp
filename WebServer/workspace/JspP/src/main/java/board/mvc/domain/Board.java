package board.mvc.domain;

import java.sql.Date;

public class Board {
	private Long seq;
	private String writer;
	private String email;
	private String subject;
	private String content;
	
	public Board() {}
	
	public Board(long seq, String writer, String email, String subject, String content) {
		this.seq = seq;
		this.writer = writer;
		this.email = email;
		this.subject = subject;
		this.content = content;
	}
	
	public long getSeq() {
		return seq;
	}
	
	public void setSeq(long seq) {
		this.seq = seq;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
