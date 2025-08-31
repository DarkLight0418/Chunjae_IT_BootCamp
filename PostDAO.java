package com.gotit.board;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PostDAO {
	private DataSource ds;
//	private static PostDAO getInstance() {
//		return instance;
//	}
	
	PostDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("");
		} catch (NamingException ne) {
			System.out.println("네이밍 예외 발생 : " + ne);
		}
	}
	
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try { if (rs != null) rs.close(); } catch (Exception e) {}
		try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
		try { if (conn != null) conn.close(); } catch (Exception e) {}
	}
}
