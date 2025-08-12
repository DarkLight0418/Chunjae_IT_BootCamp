<%@ page contentType="text/html;charset=utf-8" import="java.sql.*" %>

<%! 
	String maria = "org.mariadb.jdbc.Driver";
	String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";

    String sql = "select writer, email, subject, content, rdate from board WHERE seq=?";

	Connection con;
	PreparedStatement pstmt;

	ResultSet rs = null;
    
    public void jspInit(){ //DB연결 
		try{
			Class.forName(maria);
			System.out.println("(1) 드라이버 로딩");
			con = DriverManager.getConnection(mariaUrl, "scott", "tiger");
			System.out.println("(2) DB연결 성공");
			pstmt = con.prepareStatement(sql);
			System.out.println("(3) stmt객체생성 성공");
		}catch(ClassNotFoundException cnfe){
			System.out.println("(1) 드라이버 실패");
		}catch(SQLException se){
			System.out.println("(2) DB연결 or (3) pstmt객체생성 실패: " + se);
		}
	}
	public void jspDestroy(){ 
		try{
			pstmt.close();
			con.close();
		}
		catch (SQLException se ){
            System.out.println("Destroy 예외" + se);
        }
    }
%>
<% 
    //1. Take
	int seq = Integer.parseInt(request.getParameter("seq"));
	

	//2. DB에 insert 
	try{	
		pstmt.setInt(1, seq);

        System.out.println("modifyList seq :" + seq);		
        
    } catch(SQLException se){
        System.out.println("sql 예외:" + se);
    }
    
    ResultSet rs = null;
	try {

        rs = pstmt.executeQuery();
		while(rs.next()){
			String writer = rs.getString(1);
			String email = rs.getString(2);
			String subject = rs.getString(3);
			String content = rs.getString(4);
			Date rdate = rs.getDate(5);
        
%>

<meta charset='utf-8'>
<style>
    table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
a { text-decoration:none }
</style>
<center>
    <hr width='600' size='2' noshade>
    <h2>글 조회</h2>
    &nbsp;&nbsp;&nbsp;
    <a href='input.jsp'>글쓰기</a>
    <hr width='600' size='2' noshade>
    <table border='1' width='600' align='center' cellpadding='3'>
        <tr>
            <td width='100' align='center'>글번호</td>
            <td><%=seq%></td>
        </tr>
        <tr>
            <td align='center'>글쓴이</td>
            <td><%=writer%></td>
        </tr>
        <tr>
            <td align='center'>이메일</td>
            <td><%=email%></td>
        </tr>
        <tr>
            <td align='center'>글제목</td>
            <td><%=subject%></td>
        </tr>
        <tr>
            <td align='center'>글내용</td>
            <td><%=content%></td>
        </tr>
    </table>
    <hr width='600' size='2' noshade>
    <b>
        <a href='update_page.jsp?seq=<%=seq%>'>수정(미구현)</a>
        <a href='del.jsp?seq=<%=seq%>'>삭제(미구현)</a>
        <a href='list.jsp'>목록</a>
    </b>
    <hr width='600' size='2' noshade>
</center>
<%
        }
    } catch(SQLException se) {
		System.out.println("sql 예외 : " + se);
	} finally{
		try{
			rs.close();
		}catch(SQLException sec){
            System.out.println("sql close 예외 : " + sec);
        }
	}

%>
