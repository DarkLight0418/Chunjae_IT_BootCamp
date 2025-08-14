package addr.mvc.domain;

import java.sql.Date;

public class Address {
	private long seq;
	private String name;
	private String addr;
	private Date rdate;
	
	public Address() {} // 기본 생성자 필수
	public Address(long seq, String name, String addr, Date rdate) {
		this.seq = seq;
		this.name = name;
		this.addr = addr;
		this.rdate = rdate;
	}
	
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

