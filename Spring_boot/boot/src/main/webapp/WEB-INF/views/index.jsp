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
<a href="board_page/list.do">페이징 게시판</a>(미션)<br/><br/>
<a href="file/list.do">파일 리스트</a><br/>
<a href="board_page/list.do">페이징&파일 게시판</a>(미션)<br/><br/>
</body>