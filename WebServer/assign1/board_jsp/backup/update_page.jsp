<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>

<%!  // %! - 정의문, 선언만 해줄 것
	String maria = "org.mariadb.jdbc.Driver";
	String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
	Connection con;
	PreparedStatement pstmt;
	String sql = "UPDATE board SET email=?, subject=?, content=? WHERE seq=?";
	public void jspInit(){  
		try{
			Class.forName(maria);
			pln("(1) 드라이버 로딩");

			con = DriverManager.getConnection(mariaUrl, "scott", "tiger");
			pln("(2) DB연결 성공");

			pstmt = con.prepareStatement(sql);
			pln("(3) stmt객체생성 성공");
		
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){}
	}
	public void jspDestroy(){ 
		try{
			pstmt.close();
			con.close();
		}
		catch (SQLException se ){}
	}
	void pln(String str){
		System.out.println(str);
	}	
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
<h2>Simple Board with JSP</h2>
&nbsp;&nbsp;&nbsp;
<a href='list.jsp'>글목록</a>
<hr width='600' size='2' noshade>

<%
	//1. Take 
	int seq = Integer.parseInt(request.getParameter("seq"));
	String email = request.getParameter("email");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	
	//2. DB에 insert 
	try{	
		pstmt.setInt(1, seq);
		pstmt.setString(2, email);
		pstmt.setString(3, subject);
		pstmt.setString(4, content);
		pstmt.executeUpdate();
	
%>

<form name='f' method='post' action='update.jsp'>

    <input type="hidden" name="seq" value=<%=seq%>>
    <input type='hidden' name='writer' value=<%=writer%>>
    <table border='1' width='600' align='center' cellpadding='3' cellspacing='1'><tr>
    <td width='30%' align='center'>글쓴이</td>
        <td align='center'><input type='text' name='aa' size='60' value='최윤서' disabled></td>
    </tr>
    <tr>
    <td width='30%' align='center'>이메일</td>
    <td align='center'><input type='text' name='email' size='60' value='choi@gmail.com'></td>
    </tr>
    <tr>
    <td width='30%' align='center'>글제목</td>
    <td align='center'><input type='text' name='subject' size='60' value='저는최윤서입니다'></td>
    </tr>
    <tr>
        <td width='30%' align='center'>글내용</td>
        <td align='center'><textarea name='content' rows='5' cols='53'>감사합니다</textarea></td>
    </tr>
    <tr>
        <td colspan='2' align='center'>
        <input type='submit' value='수정'>
        </td>
    </tr>
</table>
</form>
</table>
<hr width='600' size='2' noshade>
<b>
<a  href='update.jsp?seq=<%=seq%>'>수정</a>
| 
<a href='delete.jsp?seq=<%=seq%>'>삭제</a>
| 
<a href='list.jsp'>목록</a>
</b>
<hr width='600' size='2' noshade>
</center>

<% 
    }catch(SQLException se){
		System.out.println("sql 셋팅 예외 : " + se);
    }
%>