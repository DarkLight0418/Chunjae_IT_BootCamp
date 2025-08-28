package addr.mvc.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import addr.mvc.domain.Address;
import static addr.mvc.model.AddrSQL.*;

class AddrDAO {
	private DataSource ds;
	AddrDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestDB");
		}catch(NamingException ne){
			System.out.println("DbcpBean() 예외: " + ne);
		}
	}
	ArrayList<Address> list() {
		ArrayList<Address> list = new ArrayList<Address>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			stmt = con.createStatement();

			rs = stmt.executeQuery(LIST);
			while(rs.next()){
				int seq = rs.getInt(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				java.sql.Date rdate = rs.getDate(4);
				list.add(new Address(seq, name, addr, rdate));
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
	
	boolean insert(Address dto) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
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

	//for Ajax
    Address selectBySeq(long seq){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(SELECT_SEQ);
            pstmt.setLong(1, seq);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                //int seq = rs.getLong(1);
                String name = rs.getString(2);
                String addr = rs.getString(3);
                java.sql.Date rdate = rs.getDate(4);

                return new Address(seq, name, addr, rdate);
            }else {
                return null;
            }
        }catch(SQLException se){
            return null;
        }finally{
            try{
                rs.close();
                pstmt.close();
                con.close();
            }catch(SQLException se){}
        }
    }
    ArrayList<Address> selectByName(String na){
        ArrayList<Address> list = new ArrayList<Address>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(SELECT_NAME);
            pstmt.setString(1, "%"+na+"%");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                long seq = rs.getLong(1);
                String name = rs.getString(2);
                String addr = rs.getString(3);
                java.sql.Date rdate = rs.getDate(4);

                list.add(new Address(seq, name, addr, rdate));
                System.out.println(list);
            }
            return list;
        }catch(SQLException se){
            return null;
        }finally{
            try{
                rs.close();
                pstmt.close();
                con.close();
            }catch(SQLException se){}
        }
    }
}
