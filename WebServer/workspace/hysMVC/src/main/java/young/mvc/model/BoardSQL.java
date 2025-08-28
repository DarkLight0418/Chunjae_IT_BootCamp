package young.mvc.model;

class BoardSQL {
	final static String LIST =  "select * from BOARD order by SEQ desc";
	final static String INSERT = "insert into BOARD(writer, email, subject, content, fname, ofname, rdate) values(?,?,?,?,?,?,now())";
	final static String DELETE = "delete from BOARD where SEQ=?";
	final static String SHOW = "select * from BOARD where SEQ=?";
	final static String UPDATE = "update BOARD set writer=?, email=?, subject=? ,content=? , fname=?, ofname=?, rdate=now() where seq=?";
}
