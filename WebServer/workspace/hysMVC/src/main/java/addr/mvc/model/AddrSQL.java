package addr.mvc.model;

class AddrSQL {
	final static String LIST =  "select * from ADDRESS order by SEQ desc";
	final static String INSERT = "insert into ADDRESS(name, addr, rdate) values(?,?,now())";
	final static String DELETE = "delete from ADDRESS where SEQ=?";
	
	final static String SELECT_SEQ = "select * from ADDRESS where SEQ=?";
	final static String SELECT_NAME = "select * from ADDRESS where NAME LIKE ? order by NAME";
}
