package soo.dbcp;

import javax.naming.*;
import javax.sql.DataSource;

public class DbcpBean {
	private DataSource ds;
	public DbcpBean(){
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestDB");
		}catch(NamingException ne){
			System.out.println("DBCP객체(jdbc/TestDB)를 못찾음");
		}
	}
	public DataSource getDs(){
		return ds;
	}
}