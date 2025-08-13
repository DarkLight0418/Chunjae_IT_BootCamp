package addr.mv.model;

// DTO -> 데이터 담는 상자 역할

import java.sql.Date;

public class AddrDTO_board {
	private long seq;
	private String writer;
	private String email;
	private String subject;
	private String content;
	private Date rdate;
	
	public AddrDTO_board() {}
	public AddrDTO_board(long seq, String writer, String email, String subject, String content, Date rdate) {
		this.seq = seq;
		this.writer = writer;
		this.email = email;
		this.subject = subject;
		this.content = content;
		this.rdate = rdate;
	}
	
	
	// getter
	public long getSeq() {
		return seq;
	}
	public String getWriter() {
		return writer;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getContent() {
		return content;
	}
	
	public Date getRdate() {
		return rdate;
	}
	
	// setter
	
	public void setSeq(long seq) {
		this.seq = seq;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	
}

