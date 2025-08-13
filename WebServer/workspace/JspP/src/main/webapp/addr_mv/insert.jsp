<%@ page contentType="text/html;charset=utf-8" 
     import="addr.mv.model.AddrDTO"%>
<jsp:useBean id="addrDAO" class="addr.mv.model.AddrDAO" scope="application"/>

<%
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	AddrDTO dto = new AddrDTO(-1L, name, addr, null);
	
	boolean flag = addrDAO.insert(dto);
%>
<script>
    if(<%=flag%>){
		alert("입력성공(with Model1)");
	}else  {
		alert("입력실패(with Model1)");
	}
	
    location.href="list.jsp";
</script>