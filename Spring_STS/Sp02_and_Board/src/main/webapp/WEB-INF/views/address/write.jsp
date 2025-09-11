<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>작성 폼</title>

  <style>
    table, th, td { border: 1px solid #000; border-collapse: collapse; }
    th, td { padding: 5px; }
    a { text-decoration: none; }
    .err { color:#c00; font-weight:bold; }
  </style>

  <!-- ★ 변경점 #2: 외부 스크립트 로딩 시 컨텍스트 경로를 붙였습니다. (경로 깨짐 방지) -->
  <script src="/js/trim.js" defer></script>

  <!-- ★ 변경점 #3: 구식 <script language="javascript"> 제거, defer 사용 (DOM 파싱 후 실행) -->
  <script defer>
    // --------------------------- 학습 포인트 요약 ---------------------------
    // (A) 전역 window.f, window.event 의존 X → 항상 안전하게 document.forms['f'], 이벤트 인자를 사용
    // (B) 상대경로 action → 컨텍스트 포함 절대경로
    // (C) onload 속성 대신 DOMContentLoaded 리스너로 포커스/바인딩
    // (D) 기존 check() 함수는 유지하되 실제 검증/제출은 validateAndSubmit()로 위임
    // ----------------------------------------------------------------------

    // ★ 변경점 #4: 전역 변수/암묵적 전역 사용 방지. 항상 함수로 폼 객체를 가져옵니다.
    function getForm() {
      return document.forms['f'];
    }

    // (한글을 2바이트로 간주하는) 바이트 길이 계산
    function getByteLen(str) {
      // 기존 정규식과 동등한 동작: ASCII 1바이트, 그 외 2바이트 취급
      return str.replace(/[\0-\x7f]|([^\0-\x7f])/g, "$&$1").length;
    }

    function checkByteLen(str, size) {
      return getByteLen(str) <= size;
    }

    // ★ 변경점 #5: 실제 검증/제출 로직을 분리 → 재사용/테스트가 쉬움
    function validateAndSubmit() {
      const f = getForm();

      // trim.js 가 있으면 그걸 사용, 없으면 String.trim() 대체
      const trimFn = (typeof trim === 'function')
        ? trim
        : function (s) { return (s || '').toString().trim(); };

      const nameOld = f.name.value;
      const addrOld = f.addr.value;

      const nameval = trimFn(nameOld);
      const addrval = trimFn(addrOld);

      if (nameval.length === 0) {
        alert("이름을 넣어주세요");
        f.name.value = "";
        f.name.focus();
        return false;
      }
      // ★ 기존 요구사항 유지: 이름 최대 10바이트
      if (!checkByteLen(nameOld, 10)) {
        alert("이름이 너무 길어요");
        f.name.focus();
        return false;
      }

      if (addrval.length === 0) {
        alert("주소를 넣어주세요");
        f.addr.value = "";
        f.addr.focus();
        return false;
      }
      // ★ 기존 요구사항 유지: 주소 최대 20바이트
      if (!checkByteLen(addrOld, 20)) {
        alert("주소가 너무 길어요");
        f.addr.focus();
        return false;
      }

      // 모든 검증 통과 → 제출
      f.submit();
      return true;
    }

    // ★ 변경점 #6: 전역 event 의존 X → 이벤트 객체를 반드시 인자로 받습니다.
    //    - 기존: enterCheck(this)  (window.event 에 의존, 브라우저마다 다름)
    //    - 변경: enterCheck(event, this)
    function enterCheck(e, elm) {
      // key 또는 keyCode를 모두 지원 (구버전 호환)
      const isEnter = (e && (e.key === 'Enter' || e.keyCode === 13));
      if (!isEnter) return;

      e.preventDefault(); // 엔터로 폼이 바로 제출되는 기본 동작 방지

      const f = getForm();
      if (elm === f.name) {
        // 이름 칸에서 엔터 → 주소 칸으로 포커스
        f.addr.focus();
      } else {
        // 주소 칸에서 엔터 → 검증 후 제출
        validateAndSubmit();
      }
    }

    // ★ 변경점 #7: body onload 대신 DOMContentLoaded에서 안전하게 초기화
    document.addEventListener('DOMContentLoaded', function () {
      const f = getForm();

      // 첫 포커스
      if (f && f.name) f.name.focus();

      // 만약 인라인 onkeydown을 제거하고 싶다면, 아래처럼 addEventListener 사용 가능:
      // f.name.addEventListener('keydown', (e) => enterCheck(e, f.name));
      // f.addr.addEventListener('keydown', (e) => enterCheck(e, f.addr));
      // (이번 버전은 인라인 방식을 유지하되 event 인자를 넘기도록 마크업만 보정)
    });

    // ★ 변경점 #8: 기존 onclick="check()"을 그대로 쓰고 싶어 하셨으므로
    //              check()는 validateAndSubmit()을 호출만 하도록 유지했습니다.
    function check() {
      return validateAndSubmit();
    }
  </script>
</head>
<!-- ★ 변경점 #1: action의 상대경로 문제를 피하기 위해 컨텍스트 경로를 변수로 뽑아 사용합니다.
      - 기존: action="address/write.do"  (상대경로라서 현재 JSP 경로에 따라 오작동 가능)
      - 변경: action="<%=ctx%>/address/write.do"  (항상 /앱컨텍스트/address/write.do 로 전송)
  -->
<body>
<center>
  <h1>Address Input with Spring5</h1>

  <!-- ★ 변경점 #1 적용: action에 컨텍스트 경로 포함 -->
  <form name="f" action="<%=ctx%>/address/write.do" method="post" accept-charset="UTF-8">
    <!-- ★ 선택 사항: 스프링 시큐리티 사용 시 CSRF 토큰 -->
    <c:if test="${not empty _csrf}">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </c:if>

    <table width="300" height="200">
      <tr>
        <td width="30%" colspan="2" align="center"><h2>입력폼</h2></td>
      </tr>
      <tr>
        <th width="30%">이름</th>
        <!-- ★ 변경점 #6 반영: onkeydown에서 event 인자를 함께 전달 -->
        <!--    기존: onkeydown="enterCheck(this)"  →  변경: onkeydown="enterCheck(event, this)" -->
        <td><input type="text" name="name" size="20" onkeydown="enterCheck(event, this)"></td>
      </tr>
      <tr>
        <th width="30%">주소</th>
        <td><input type="text" name="addr" size="20" onkeydown="enterCheck(event, this)"></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <!-- ★ 기존 형태 유지: onclick="check()" 그대로 사용 (내부 위임만 변경) -->
          <input type="button" value="전송" onclick="check()"/>
          <input type="reset" value="취소"/>
        </td>
      </tr>
    </table>
  </form>
</center>
</body>
</html>
