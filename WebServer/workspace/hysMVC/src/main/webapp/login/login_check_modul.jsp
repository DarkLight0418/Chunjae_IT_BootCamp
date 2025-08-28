<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<meta charset="utf-8">
<c:if test="${empty loginOkUser}">
	<script>
		alert("회원 서비스 입니다 로그인하세요");
		location.href="../login/login.do?m=form"
	</script>
</c:if>