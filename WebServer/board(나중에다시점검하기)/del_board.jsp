<%@ page contentType="text/html;charset=utf-8"%>
<jsp:useBean id="addrDAO" class="addr.mv.model.AddrDAO" scope="application"/>

<% 
	String strSeq = request.getParameter("seq");
	int seq = Integer.parseInt(strSeq);
	
	boolean flag = addrDAO.delete(seq);
%>
<script>
	if(<%=flag%>){
		alert("삭제성공(with Model1)");
	}else  {
		alert("삭제실패(with Model1)");
	}
	
	location.href="list.jsp";
</script>