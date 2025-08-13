package addr.mv.model;

// DTO -> 데이터 담는 상자 역할

import java.sql.Date;

public class AddrDTO {
	private long seq;
	private String name;
	private String addr;
	private Date rdate;
	
	public AddrDTO() {}
	public AddrDTO(long seq, String name, String addr, Date rdate) {
		this.seq = seq;
		this.name = name;
		this.addr = addr;
		this.rdate = rdate;
	}
	
	
	// getter
	public long getSeq() {
		return seq;
	}
	
	public String getName() {
		return name;
	}
	public String getAddr() {
		return addr;
	}
	
	public Date getRdate() {
		return rdate;
	}
	
	// setter
	
	public void setSeq(long seq) {
		this.seq = seq;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	
}

