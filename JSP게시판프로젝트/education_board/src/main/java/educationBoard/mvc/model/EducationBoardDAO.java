package educationBoard.mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import educationBoard.mvc.domain.EducationBoard;

public class EducationBoardDAO {
	private DataSource ds;
	public EducationBoardDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			ds = (DataSource) envContext.lookup("jdbc/TestDB");
		} catch (NamingException ne) {
			System.out.println("naming 예외 발생 : " + ne);
		}
	}
	
	public ArrayList<EducationBoard> list() {
		ArrayList<EducationBoard> list = new ArrayList<>();
		
		// String sql = EducationBoardSQL.LIST;
		
		String sql = "";
		
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				EducationBoard eb = new EducationBoard();
				// eb set(rs.get());
			}
		} catch (SQLException se) {
			System.out.println("조회 SQL 예외 : " + se);
		}
		return list;
	}
	
	public void insert(EducationBoard dto) {
		String sql = "";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
					// pstmt.set(dto.get());
			// pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println("삽입 SQL 예외 : " + se);
		}
	}
	
	public void delete(long seq) {
		String sql = "";
		
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, seq);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println("삭제 SQL 예외 : " + se);
		}
	}
	
	public EducationBoard content(EducationBoard dto) {
		String sql = "";
		EducationBoard result = null;
		
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// pstmt.setLong(1, dto.getSeq());
		
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					result = new EducationBoard();
					// result.set(rs.get());
				}
			}
		}
		catch (SQLException se) {
			System.out.println("게시물 조회 SQL 예외 : " + se);
		}
		
		return result;
	}
	
	public void update(EducationBoard dto) {
		String sql = "";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// pstmt.set(dto.get());
		} catch (SQLException se) {
			System.out.println("수정 SQL 예외 : " + se);
		}
	}
}
