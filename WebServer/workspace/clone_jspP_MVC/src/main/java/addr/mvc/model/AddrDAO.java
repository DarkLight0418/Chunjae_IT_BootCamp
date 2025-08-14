package addr.mvc.model;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import addr.mvc.domain.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;


class AddrDAO {
	private DataSource ds;
	AddrDAO() {
		try {  // JNDI 코드
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/TestDB");
		} catch (NamingException ne) {}
	}
	ArrayList<Address> list() {
		ArrayList<Address> list = new ArrayList<Address>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from ADDRESS order by SEQ desc";
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		
			while(rs.next()) {
				int seq = rs.getInt(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				java.sql.Date rdate = rs.getDate(4);
				
				list.add(new Address(seq, name, addr, rdate));
			}
			return list;
		} catch(SQLException se) {
			return null;
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch(SQLException se) {}
		}
	}
	boolean insert(Address dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		String sql = "insert into ADDRESS(name, addr, rdate) values(?,?,now())";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			int i = pstmt.executeUpdate();
			if(i > 0) return true;
			else return false;
		} catch(SQLException se) {
			return false;
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch(SQLException se) {}
		}
	}
	boolean delete(long seq) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from ADDRESS where SEQ=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			int i = pstmt.executeUpdate();
			if(i > 0) return true;
			else return false;
		} catch (SQLException se) {
			return false;
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException se) {}
		}
	}
}
