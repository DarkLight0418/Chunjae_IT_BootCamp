<%@ page contentType="text/html;charset=utf-8" 
         import="java.util.ArrayList, board.mvc.domain.Board" %>

<meta charset='utf-8'>

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
a {
    text-decoration: none;
}
</style>

<center>
<h1>
Board List (Model2)
</h1>
<a href='board.do?m=input'>입력</a>
<a href='../'>인덱스</a>

<table border='1' cellpadding='7' cellspacing='2' width='80%'>
<tr>
    <th>번호</th>
    <th>작성자</th>
    <th>이메일</th>
    <th>제목</th>
    <th>삭제</th>
</tr>

<%
    ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
    if(list != null && list.size() != 0){
        for(Board dto : list){
%>
<tr>
    <td align='center'><%= String.valueOf(dto.getSeq()) %></td>
    <td><%=dto.getWriter()%></td>
    <td><%=dto.getEmail()%></td>
    <td>
        <a href='board.do?m=view&seq=<%=dto.getSeq()%>'>
            <%=dto.getSubject()%>
        </a>
    </td>
    <td align='center'>
        <a href='board.do?m=del&seq=<%=dto.getSeq()%>' onclick="return confirm('정말 삭제할까요?')">삭제</a>
    </td>
</tr>
<%
        }
    } else {
%>
<tr>
    <td align='center' colspan="5">데이터가 하나도 없음</td>
</tr>
<%
    }
%>

</table>
</center>

