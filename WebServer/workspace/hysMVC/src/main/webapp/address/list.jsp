<%@ page contentType="text/html;charset=utf-8" 
     import="java.util.ArrayList, addr.mvc.domain.Address"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="../login/login_check_modul.jsp" />

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
<h1>
Address List (EL)
</h1>
<a href='addr.do?m=input'>입력</a> 
&nbsp;&nbsp;&nbsp;
<a href='../'>인덱스</a>
<table border='1' cellpadding='7' cellspacing='2' width='50%'>
<tr>
<th>번호</th>
<th>이름</th>
<th>주소</th>
<th>날짜</th>
<th>삭제</th>
</tr>


<c:if test="${empty list}">
    <tr>
		<td align='center' colspan="5">데이터가 하나도 없음</td>
	</tr>
</c:if>
<c:forEach items="${list}" var="dto">
			<tr>
			<td align='center'>${dto.seq}</td>
			<td>${dto.name}</td>
			<td>${dto.addr}</td>
			<td>${dto.rdate}</td>
			<td align='center'><a href='addr.do?m=del&seq=${dto.seq}'>삭제</a></td>
			</tr>
</c:forEach>
<%--
<c:choose>
<c:when test="${empty list}">
    <tr>
		<td align='center' colspan="5">데이터가 하나도 없음^^</td>
	</tr>
</c:when>
<c:otherwise>
	<c:forEach items="${list}" var="dto">
		<tr>
			<td align='center'>${dto.seq}</td>
			<td>${dto.name}</td>
			<td>${dto.addr}</td>
			<td>${dto.rdate}</td>
			<td align='center'><a href='addr.do?m=del&seq=${dto.seq}'>삭제^^</a></td>
		</tr>
	</c:forEach>
</c:otherwise>
</c:choose>
--%>

</table>
</center>
