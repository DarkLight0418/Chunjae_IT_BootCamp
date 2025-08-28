package board.mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import board.mvc.domain.Board;

public class BoardDAO {
	private DataSource ds;
	BoardDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			ds = (DataSource) envContext.lookup("jdbc/TestDB");
		} catch (NamingException ne) {
			System.out.println("naming 예외 발생 : " + ne);
		}
	}
	
	public ArrayList<Board> list() {
		ArrayList<Board> list = new ArrayList<>();
		String sql = BoardSQL.LIST;
		
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				Board b = new Board();
				b.setSeq(rs.getLong("seq"));
				b.setWriter(rs.getString("writer"));
				b.setEmail(rs.getString("email"));
				b.setSubject(rs.getString("subject"));
				b.setContent(rs.getString("content"));
			}
		} catch (SQLException se) {
			System.out.println("SQL 예외 : " + se);
		}
		return list;
	}
	
	public void insert(Board dto) {
		String sql = BoardSQL.INSERT;
		try (Connection conn = ds.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println("SQL 예외 : " + se);
		}
	}
	
	public void delete(long seq) {
		String sql = BoardSQL.DELETE;
		
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setLong(1, seq);
			pstmt.executeUpdate();
	
		} catch (SQLException se) {
			System.out.println("SQL 예외 : " + se);
		}
	}
	
	public Board content(Board dto) {
		String sql = BoardSQL.UPDATECONTENT;
		Board result = null;
		try (Connection conn = ds.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, dto.getSeq());
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					result = new Board();
					result.setSeq(rs.getLong("seq"));
					result.setWriter(rs.getString("writer"));
					result.setEmail(rs.getString("email"));
					result.setSubject(rs.getString("subject"));
					result.setContent(rs.getString("content"));
				}
			}
		} catch (SQLException se) {
			System.out.println("SQL 예외 : " + se);
		}
		return result;
	}
	
	public void update(Board dto) {
		String sql = BoardSQL.DOUPDATE;
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setLong(4, dto.getSeq());
		} catch (SQLException se) {
			System.out.println("SQL 예외 : " + se);
		}
	}
}
