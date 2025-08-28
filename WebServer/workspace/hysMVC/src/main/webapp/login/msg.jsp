<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "login.mvc.model.LoginConst" %>

<script>
	const result = ${result};
	
	let message = "";
	
	if(result === <%=LoginConst.NO_ID%>) {
	    message = "아이디가 틀립니다.";
	    location.href = "login.do?m=form";
	}else if(result === <%=LoginConst.NO_PWD%>) {
		message = "패스워드가 다릅니다";
		location.href = "login.do?m=form";
	}else if(result === <%=LoginConst.YES_ID_PWD%>) {
		message = "로그인에 성공하셨습니다";
		location.href = "../";
	}
	alert(message);
</script>