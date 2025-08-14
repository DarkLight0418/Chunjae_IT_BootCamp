<%@ page contentType="text/html;charset=utf-8" 
     import="addr.mv.model.AddrDTO_board"%>
<jsp:useBean id="addrDAO_board" class="addr.mv.model.AddrDAO_board" scope="application"/>

<%
	String writer = request.getParameter("writer");
	String email = request.getParameter("email");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	AddrDTO_board dto = new AddrDTO_board(-1L, writer, email, subject, content, null);
	
	boolean flag = addrDAO_board.insert(dto);
%>
<script>
    if(<%=flag%>){
		alert("입력성공(with Model1)");
	}else  {
		alert("입력실패(with Model1)");
	}
	
    location.href="list_board.jsp";
</script>