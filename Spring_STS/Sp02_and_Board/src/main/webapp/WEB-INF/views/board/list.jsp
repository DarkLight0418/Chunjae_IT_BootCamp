<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
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
<h3> Spring Board</h3>
<font color='gray' size='4' face='휴먼편지체'>
<a href='../'>인덱스</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
</TR>

<c:if test="${empty boardList}">
	<tr align="center">
		<td align="center" colspan="5">데이터가 하나도 없어요 ㅜㅜ</td>
	</tr>
</c:if>

<c:forEach items="${boardList}" var="boardList">     
    <TR align='center' noshade>
		<TD>${boardList.seq}</TD>
		<TD>${boardList.writer}</TD>
		<TD>${boardList.email}</TD>
	    <TD>
	      <a href="content.do?seq=${boardList.seq}">
		    ${boardList.subject}
		  </a>
		</TD>
		<TD>${boardList.rdate}</TD>
	   </TR> 
</c:forEach>

</TABLE>
<hr width='600' size='2' color='gray' noshade>
<font color='gray' size='3' face='휴먼편지체'>
    (총페이지수 : ${pageInfo.maxPage}) &nbsp;
    &nbsp;&nbsp;
    
    <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="i">
        <a href="list.do?cp=${i}&ps=${pageInfo.pageNum}">
            <c:choose>
            	<c:when test="${i == pageInfo.pageNum}">
                	<strong>${i}</strong>
                </c:when>
                <c:otherwise>
                	<a href="list.do?cp=${i}&ps=${pageInfo.listLimit}">[${i}]</a>
                </c:otherwise>
            </c:choose>
    	</a>&nbsp;
    </c:forEach>
    ( TOTAL : ${pageInfo.listCount} )
    
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       페이지 사이즈 : 
    <select id="psId" name="ps" onchange="f(this)">
	   <option value="3" ${pageInfo.listLimit == 3 ? 'selected' : ''}>3</option>
       <option value="5" ${pageInfo.listLimit == 5 ? 'selected' : ''}>5</option>
       <option value="10" ${pageInfo.listLimit == 10 ? 'selected' : '' }>10</option>
    </select>
    
    <script language="javascript">
       function f(select){
           //var el = document.getElementById("psId");
           var ps = select.value;
           //alert("ps : " + ps);
           location.href="list.do?cp="${i}"&ps="+ps;
       }
    </script>
    
</font>
<hr width='600' size='2' color='gray' noshade>
<!--
<form action="">
      <select name="catgo">
        <option value="subject">제목</option>
        <option value="writer">글쓴이</option>
        <option value="content">내용</option>
      </select>
      <input type="text" name="keyword" size="40" required="required" /> <button>검색</button>
    </form> 
-->    
</center>
</body>
</html>
