<%--

<%@ page contentType="text/html;charset=utf-8" 
     import="addr.mv.model.AddrDTO_board"%>
<jsp:useBean id="addrDAO_board" class="addr.mv.model.AddrDAO_board" scope="application"/>

<meta charset="utf-8">
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
<body onload="document.f.name.focus()">
<center>

<% 
	long b = AddrDTO_board.getSeq();
	ArrayList<AddrDTO_board> content_board = addrDAO_board.contents(b);
    if(content_board != null && content_board.size() != 0){
    	for(AddrDTO_board dto : content_board){
%>
   <h1>
		게시판 수정 Model1
   </h1>
   <form name="f" action="update_content_board.jsp" method="post">
       <table border="1" width="300" height="200">
	      <tr>
		     <td width="30%" colspan="2" align="center"><h2>수정폼</h2></td> 
		  </tr>
		  
		  <tr>
		  	<th width="30%">작성자 이름</th> 
			 <td><input name="writer" align="center" size="20" align="center" value="${dto.writer}"></td>
		  </tr>
		  <tr>
		     <th width="30%">이메일</th> 
			 <td><input name="email" align="center" size="20" align="center" value="${dto.email}"></td>
		  </tr>
		  <tr>
		     <th width="30%">제목</th> 
			 <td><input name="subject" size="20" align="center" value="${dto.subject}"></td>
		  </tr>
		  
		   <tr>
		     <th width="30%">내용</th> 
			 <td><input name="content" size="20" align="center" value="${dto.content}"></td>
		  </tr>
		  
		  <tr>
		     <td colspan="2" align="center">
			     <input type="submit" value="전송"/>
				 <input type="reset" value="취소"/>
			 </td> 
		  </tr>
	   </table>
   </form>
   <%
    	} 
    }else{
%>
	   <tr>
		<td align='center' colspan="5">데이터가 조회되지 않음</td>
	   </tr>
<%    	
    }
%>
</center>
</body>

--%>