package addr.mv.model;

// DAO -> Data Access Object : DB 연결 로직들 정리

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class AddrDAO {
	private DataSource ds;
	public AddrDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestDB");
		}catch(NamingException ne){}
	}
	public ArrayList<AddrDTO> list() {
		ArrayList<AddrDTO> list = new ArrayList<AddrDTO>();
		Connection con = null;
		Statement stmt = null;
	    String sql = "select * from ADDRESS order by SEQ desc";
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int seq = rs.getInt(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				java.sql.Date rdate = rs.getDate(4);
				
				list.add(new AddrDTO(seq, name, addr, rdate));
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
	public boolean insert(AddrDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into ADDRESS(name, addr, rdate) values(?,?,now())";

		try{	
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
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
		String sql = "delete from ADDRESS where SEQ=?";
		
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
}
