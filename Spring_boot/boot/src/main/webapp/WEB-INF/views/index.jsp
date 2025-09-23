<%@ page contentType ="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<meta charset="utf-8">
<style>
    a{text-decoration:none}
</style>
<body style="text-align:center">
<h2>index.jsp </h2>

<a href="address/list.do">주소록(Jdbc)</a><br/>
<a href="board/list.do">게에시이파안(아직 안만듦)</a><br/>
<a href="address2/list.do">주소록(JdbcTemplate or mybatis or JPA)</a><br/>
<a href="th/address2/list">주소록(JdbcTemplate타임리프 or mybatis(타임리프) or JPA(타임리프))</a><br/>
<a href="board2/list.do">게시판(JPA)(아직 안만듦)</a>(미션)<br/>
<a href="address2/list.do">주소록(Spring Data JPA)</a><br/>
<a href="address_page/list.do">페이징 주소록(SpringDataJpa)</a><br/>
<br/>
<br/>
<hr>
<h3>△ 위에는 지났던 거 △</h3>

<a href="board_page/list.do">페이징 게시판</a>(미션)<br/>
<a href="file/list.do">파일 리스트</a><br/>
<a href="board_page/list.do">페이징&파일 게시판</a>(미션)<br/><br/>

<br/>
<hr>
<h3>AJAX</h3>
<a href="ajax/test1.do">Ajax1</a>&nbsp;
<a href="ajax/test2.do">Ajax2</a>&nbsp;
<a href="ajax/test3.do">Ajax3</a>&nbsp;
<a href="ajax/test4.do">Ajax4</a><br/>
<a href="auto/auto.do">자동완성</a><br/><br/>

<!--
<a href="drag_drop/form.do">Drag&Drop폼</a>&nbsp;&nbsp;&nbsp;
-->
<a href="drag_drop/list.do">Drag&Drop리스트</a><br/>
<a href="chart/chart.do">Google Chart</a><br/>
</body>