<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>


<html>
<%! // %! - 정의문, 선언만 해줄 것
	String maria = "org.mariadb.jdbc.Driver";
	String mariaUrl = "jdbc:mariadb://127.0.0.1:3306/java_schema";
	Connection con;
	PreparedStatement pstmt;
	String sql = "select * from board where seq=?";
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
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내용 수정</title>
</head>
<body>
	<h1>게시판 글 수정</h1>
<% 

    //1. Take
	int seq = Integer.parseInt(request.getParameter("seq"));
	
	ResultSet rs = null;

	//2. DB에 insert 
	try {	
		pstmt.setInt(1, seq);
		rs = pstmt.executeQuery();

        System.out.println("modifyList seq :" + seq);		
        
		if(rs.next()){
			String writer = rs.getString(2);
			String email = rs.getString(3);
			String subject = rs.getString(4);
			String content = rs.getString(5);
			Date rdate = rs.getDate(6);
		
%>
   
    <form name="수정 폼" action="update.jsp" method="post">
        <table border="1" width="400" height="300">
	      <tr>
		     <td width="30%" colspan="4" align="center"><h2>수정 폼</h2></td> 
		  </tr>

		  <tr>
		     <th width="30%">번호</th> 
			 <td><input type=hidden name=<%=seq%> size="20" readonly="readonly"><%=seq%></td> <!-- 입력을 막기 위한 readonly 속성(말 그대로 읽는 것만!) -->
		  </tr>

		  <tr>
		     <th width="30%">작성자 이름</th> 
			 <td><input type=hidden name=<%=writer%> size="20" readonly="readonly"><%=writer%></td> <!-- 입력을 막기 위한 readonly 속성(말 그대로 읽는 것만!) -->
		  </tr>

		  <tr>
		     <th width="30%">이메일</th> 
			 <td><input type=text name=<%=email%> size="20"></td>
		  </tr>

		  <tr>
		     <th width="30%">제목</th> 
			 <td><input type=text name=<%=subject%> size="20"></td>
		  </tr>
		  <tr>
		     <th width="30%">내용</th> 
			 <td><input type=text name=<%=content%> style="width:280px; height:120px; align:center;"></td>
		  </tr>
		  <tr>
		     <td colspan="2" align="center">
			     <input type="submit" value="수정"/>
				 <input type="reset" value="초기화"/>
			 </td> 
		  </tr>
	   </table>
    </form>
	<a href="index.html">인덱스 창으로</a>
	<a href="list.do">글 목록으로</a>
<%
		}
	
    } catch(SQLException se){
        System.out.println("sql 예외:" + se);
	} finally{
		try{
			rs.close();
		}catch(SQLException sec){
            System.out.println("sql close 예외 : " + sec);
        }
	}

%>

	</body>
</html>