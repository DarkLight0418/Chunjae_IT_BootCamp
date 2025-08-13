package addr.mv.model;

// DAO -> Data Access Object : DB 연결 로직들 정리

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class AddrDAO_board {
	private DataSource ds;
	public AddrDAO_board() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestDB");
		}catch(NamingException ne){}
	}
	public ArrayList<AddrDTO_board> list() {
		ArrayList<AddrDTO_board> list = new ArrayList<AddrDTO_board>();
		Connection con = null;
		Statement stmt = null;
	    String sql = "select * from Board order by SEQ desc";
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int seq = rs.getInt(1);
				String writer = rs.getString(2);
				String email = rs.getString(3);
				String subject = rs.getString(4);
				String content = rs.getString(5);
				java.sql.Date rdate = rs.getDate(6);
				
				list.add(new AddrDTO_board(seq, writer, email, subject, content, rdate));
			}
			return list;
		}catch(SQLException se){
			return null;
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException se){}
		}
	}
	public boolean insert(AddrDTO_board dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into Board(writer, email, subject, content, rdate) values(?,?,?,?,now())";

		try{	
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setDate(5, dto.getRdate());
			
			int i = pstmt.executeUpdate();
			if(i > 0) return true;
			else return false; 
		}catch(SQLException se){
			return false;
		}finally{
			try{
				pstmt.close();
				con.close();
			}catch(SQLException se){}
		}
	}
	public boolean delete(long seq) {
	    Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from Board where SEQ=?";
		
		try{	
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, seq);
			int i = pstmt.executeUpdate();
			if(i > 0) return true;
			else return false; 
		}catch(SQLException se){
			return false;
		}finally{
			try{
				pstmt.close();
				con.close();
			}catch(SQLException se){}
		}
	}
	
	public boolean update(AddrDTO_board dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update Board set email=? subject=? content=? where SEQ=?";
		try{	
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setLong(4, dto.getSeq());
			
			int i = pstmt.executeUpdate();
			if(i > 0) return true;
			else return false; 
		}catch(SQLException se){
			return false;
		}finally{
			try{
				pstmt.close();
				con.close();
			}catch(SQLException se){}
		}
	}
	public ArrayList<AddrDTO_board> contents(long seq) {
		ArrayList<AddrDTO_board> contents = new ArrayList<AddrDTO_board>();
		Connection con = null;
		PreparedStatement pstmt = null;
	    String sql = "select * from Board where SEQ=?";
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.preparedStatement(sql);
			rs = pstmt.executeQuery();
			
			pstmt.setLong(1, seq);
			
			if(rs.next()){
				
				String writer = rs.getString(2);
				String email = rs.getString(3);
				String subject = rs.getString(4);
				String content = rs.getString(5);
				java.sql.Date rdate = rs.getDate(6);
				
				contents.add(new AddrDTO_board(seq, writer, email, subject, content, rdate));
			}
			return contents;
		}catch(SQLException se){
			return null;
		}finally{
			try{
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException se){}
		}
	}
	}
}
