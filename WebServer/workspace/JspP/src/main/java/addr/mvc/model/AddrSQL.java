package addr.mvc.model;

class AddrSQL {
	final static String LIST = "select * from ADDRESS order by SEQ desc";
	final static String INSERT = "insert into ADDRESS(name, addr, rdate) values(?,?,now())";
	final static String DELETE = "delete from ADDRESS where SEQ=?";
}

