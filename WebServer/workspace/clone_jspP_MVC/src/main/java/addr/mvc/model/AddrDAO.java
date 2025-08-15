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
		
		String sql = "select * from ADDRESS";
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		
			while(rs.next()) {
				long seq = rs.getLong(1);
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
				if(rs != null) rs.close();
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
	boolean update(Address dto, long seq) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "update ADDRESS set name=?, addr=? where SEQ=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			pstmt.setLong(3, dto.getSeq());
			
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
	boolean content(long seq) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from ADDRESS where SEQ=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, seq);
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				long seqNo = rs.getLong(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				java.sql.Date rdate = rs.getDate(4);
			}
			
			return true;
		} catch (SQLException se) {
			System.out.println("sql 조회 예외 발생 : " + se);
			return false;
		} finally {
			try {
				if (rs!=null) rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException se) {
			}
		}
	}
}
