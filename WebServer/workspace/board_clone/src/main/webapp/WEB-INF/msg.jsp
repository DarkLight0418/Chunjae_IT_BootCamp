<%@ page contentType="text/html;charset=utf-8"%>

<script>
<%
    boolean flag = (Boolean)request.getAttribute("flag");
    String kind = (String)request.getAttribute("kind");
    if(kind.equals("insert")){
%>
    if(<%=flag%>){
        alert("입력성공(with Model2)");
    }else  {
        alert("입력실패(with Model2)");
    }
<%
    }else{
%>
    if(<%=flag%>){
        alert("삭제성공(with Model2)");
    }else  {
        alert("삭제실패(with Model2)");
    }
    location.href="addr.do";
<%
    }
%>
location.href="addr.do";
</script>
[출처] DAY40 FullStack Java 12기 - MVC Model2 게시판|작성자 kinhyo