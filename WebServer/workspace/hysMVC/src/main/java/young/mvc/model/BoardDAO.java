package young.mvc.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import young.mvc.domain.Board;
import static young.mvc.model.BoardSQL.*;

class BoardDAO {
	private DataSource ds;
	BoardDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestDB");
		}catch(NamingException ne){
			System.out.println("DbcpBean() 예외: " + ne);
		}
	}
	ArrayList<Board> list() {
		ArrayList<Board> list = new ArrayList<Board>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(LIST);
			while(rs.next()){
				int seq = rs.getInt(1);
	            String writer = rs.getString(2);
	            String email = rs.getString(3);
				String subject = rs.getString(4);
				String content = rs.getString(5);
				String fname = rs.getString(6);
				String ofname = rs.getString(7);
	            Date rdate = rs.getDate(8);
				list.add(new Board(seq, writer, email, subject, content, fname, ofname, rdate));
			}
			return list;
		}catch(SQLException se){
			 return null;
		}finally{
			try{
				if (rs != null) {
					rs.close();
					stmt.close();
					con.close();
				}
			}catch(SQLException se){}
		}
	}
	
	boolean insert(Board dto) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getFname());
			pstmt.setString(6, dto.getOfname());
			int i = pstmt.executeUpdate();
			if(i > 0) return true;
			else return false;
		}catch(SQLException se){
			return false;
		}
		finally{
			try{
				pstmt.close();
				con.close();
			}catch(SQLException se) {}
		}
	}
	
	public Board show(long seq) {
		Board dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(SHOW);
			pstmt.setLong(1, seq);
			rs = pstmt.executeQuery();
			if (rs.next()) {
	            String writer = rs.getString(2);
	            String email = rs.getString(3);
				String subject = rs.getString(4);
				String content = rs.getString(5);
				String fname = rs.getString(6);
				String ofname = rs.getString(7);
	            Date rdate = rs.getDate(8);
	            dto = new Board(seq, writer, email, subject, content, fname, ofname, rdate);
			}
			return dto;
		}catch (SQLException se) {
			return null;
		}finally {
			try{
				pstmt.close();
				con.close();
				}catch (SQLException se ){}
		}
	}
	
	
	boolean delete(long seq) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, seq);
			int i = pstmt.executeUpdate();
	        if(i > 0) return true;
	        else return false; 
		}catch(SQLException se){
			return false;
		}
		finally{
			try{
				pstmt.close();
				con.close();
			}catch(SQLException se) {}
		}	
	}
	
	public boolean update(long seq, String writer, String email, String subject, String content, String fname, String ofname) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, writer);
			pstmt.setString(2, email);
			pstmt.setString(3, subject);
			pstmt.setString(4, content);
			pstmt.setString(5, fname);
			pstmt.setString(6, ofname);
			pstmt.setLong(7, seq);
			int i = pstmt.executeUpdate();
			if(i > 0) return true;
			else return false;
		}catch(SQLException se){
			return false;
		}finally{
			try{
				pstmt.close();
				con.close();
			}catch(SQLException se) {}
		}
	}
}
