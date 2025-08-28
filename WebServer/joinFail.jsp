<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>회원가입 실패</title>
</head>
<body>
  <h2> 회원가입에 실패했습니다.</h2>
  <p>다시 시도해 주세요.</p>
  
  <a href="<%=request.getContextPath()%>/login.do?m=join">[회원가입 페이지로]</a><br>
  <a href="<%=request.getContextPath()%>/login.do?m=form">[로그인 페이지로]</a>
  
  <!-- 
  
  - request.getContextPath() : 현재 웹 애플리케이션의 context root 반환 (예: "/homework")
  - 최종 URL: "/homework/login.do?m=form(join)"
  
    
  
       아래는 과거 방식의 정적 경로 접근 예시 (주석 처리됨)
       - "join.html" : webapp/login/join.html 파일에 직접 접근
       - "login.jsp" : webapp/login/login.jsp 파일에 직접 접근
       ⚠️ 하지만 MVC 패턴에서는 JSP/HTML을 직접 호출하지 않고 
          반드시 Controller(login.do)를 거쳐 접근하는 것이 원칙
  
  
  
  <a href="join.html">[회원가입 페이지로]</a><br>
  <a href="login.jsp">[로그인 페이지로]</a>
	-->
</body>
</html>
