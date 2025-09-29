<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>


<html lang="ko">
<head>
	<title> Spring Board </title>
	<meta charset="utf-8">
	<style>
		a{text-decoration:none}
	</style>
</head>
<body>
<center>
<font color='gray' size='4' face='휴먼편지체'>
<hr width='600' size='2' color='gray' noshade>
<h3>Spring Board(아마도 JPA로 진행하는)</h3>
<font color='gray' size='4' face='휴먼편지체'>
<a href='../'>인덱스</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <!-- &nbsp *줄 바꿈 없는 공백(Non-breaking Space) -->
<a href='write.do'>글쓰기</a><br/>
</font>
<hr width='600' size='2' color='gray' noshade>

<TABLE border='2' width='600' align='center' noshade>
<TR size='2' align='center' noshade bgcolor='AliceBlue'>
	<th bgcolor='AliceBlue'>no</th>
	<th color='gray'>writer</th>
	<th color='gray'>e-mail</th>
	<th color='gray'>subject</th>
	<th color='gray'>date</th>
	<th color='gray'>file</th>
</TR>

<c:if test="${empty boardList}">
	<tr align="center">
		<td align="center" colspan="6">데이터가 하나도 없어요 ㅜㅜ</td>
	</tr>
</c:if>

<c:forEach items="${boardList}" var="board">
    <TR align='center' noshade>
		<TD>${board.seq}</TD>
		<TD>${board.writer}</TD>
		<TD>${board.email}</TD>
	    <TD>
	      <a href="content.do?seq=${board.seq}">
		    ${board.subject}
		  </a>
		</TD>
		<TD>${board.rdate}</TD>
		<td>
            <c:forEach items="${board.attachments}" var="file">
                <a href="download/${file.id}">
                    ${file.ofname} (${file.fsize} bytes}
                </a>
            </c:forEach>
        </td>
	   </TR> 
</c:forEach>

</TABLE>
<hr width='600' size='2' color='gray' noshade>
<font color='gray' size='3' face='휴먼편지체'>
    (총페이지수 : ${pageInfo.totalPages}) &nbsp;
    &nbsp;&nbsp;

    <c:if test="${pageInfo.prev}">
        <a href="list.do?page=${pageInfo.startPage - 2}&size=${pageInfo.pageSize}">이전</a>
	<!-- nbsp 줄 바꿈 없는 공백(Non-breaking Space) -->
    </c:if>
    
    <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="i">
            <c:choose>
            	<c:when test="${i == pageInfo.pageNum}">
                	<strong>[${i}]</strong>
                </c:when>
                <c:otherwise>
                	<a href="list.do?page=${i-1}&size=${pageInfo.pageSize}">[${i}]</a>
                </c:otherwise>
            </c:choose>
    	&nbsp;
    </c:forEach>
    <c:if test="${pageInfo.next}">  <!-- 아예 태그를 받아올거임 컨트롤러에서 -->
        <a href="list.do?page=${pageInfo.endPage}&size=${pageInfo.pageSize}">다음</a>
    </c:if>

    <!-- 기존 boardList 변수를 통해 다 받아왔다면 아예 페이징 파트를 리스트 파트와 로직 따로 분리함 -->
    
    
    ( TOTAL : ${pageInfo.totalElements} )
    
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       페이지 사이즈 : 
    <select id="psId" name="ps" onchange="f(this)">
	   <option value="3" ${pageInfo.pageSize == 3 ? 'selected' : ''}>3</option>
       <option value="5" ${pageInfo.pageSize == 5 ? 'selected' : ''}>5</option>
       <option value="10" ${pageInfo.pageSize == 10 ? 'selected' : '' }>10</option>
    </select>
    
    <script language="javascript">
       function f(select){
           //var el = document.getElementById("psId");
           let cp = select.value;
           var ps = select.value;
           //alert("ps : " + ps);
           location.href="list.do?page=0&size="+ps;
       }
    </script>
    
</font>
<hr width='600' size='2' color='gray' noshade>

	<form action="${pageContext.request.contextPath}/board_page/search.do" method="get">
      <select name="type">
        <option value="subject" ${type == 'subject' ? 'selected' : ''}">제목</option>
        <option value="writer" ${type == 'writer' ? 'selected' : ''}>글쓴이</option>
        <option value="content" ${type == 'content' ? 'selected' : ''}>내용</option>
      </select>
      <input type="text" name="keyword" size="40" required="required" /> <button>검색</button>
    </form> 
      
</center>
</body>
</html>
