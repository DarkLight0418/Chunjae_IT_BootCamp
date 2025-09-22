<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
        }
        a { text-decoration:none }
    </style>
</head>

<body style="text-align:center">
<center>
    <h1>
        Pageable Address List with SpringBoot
    </h1>
    <a href='write.do'>입력</a><br/>
    <table border='1' cellpadding='7' cellspacing='2' width='50%'>
        <tr>
            <th>번호</th>
            <th>이름</th>
            <th>주소</th>
            <th>날짜</th>
            <th>삭제</th>
        </tr>
        <c:if test="${empty listResult.list.content}">
            <tr>
                <td align="center" colspan="5">데이터가 없음 ㅠㅠ</td>
            </tr>
        </c:if>
        <c:forEach items="${listResult.list.content}" var="address">
        <tr>
            <td align='center'>${address.seq}</td>
            <td>${address.name}</td>
            <td>${address.addr}</td>
            <td>
                <fmt:formatDate value="${address.rdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td align='center'><a href='del.do?seq=${address.seq}'>삭제</a></td>
        </tr>
        </c:forEach>

    </table>

    <hr width='600' size='2' color='gray' noshade>
    <font color='gray' size='3' face='휴먼편지체'>
        (총페이지수 : ${listResult.totalPageCount})
        &nbsp;&nbsp;&nbsp;
        <c:forEach begin="0" end="${listResult.totalPageCount-1}" var="i">
            <a href="list.do?page=${i}">
                <c:choose>
                    <c:when test="${i==listResult.page}">
                        <strong>${i+1}</strong>
                    </c:when>
                    <c:otherwise>
                        ${i+1}
                    </c:otherwise>
                </c:choose>
            </a>&nbsp;
        </c:forEach>
        ( TOTAL : ${listResult.totalCount} )

        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <!-- 페이지 크기(페이지당 게시글 수)를 선택하는 드롭다운 -->
        페이지 싸이즈 :
        <select id="psId" name="ps" onchange="f(this)">
            <!-- JSTL choose문: 조건에 따라 어떤 option을 선택(selected) 상태로 보여줄지 결정 -->
            <c:choose>
                <!-- 만약 현재 listResult.size 값이 3이라면 -->
                <c:when test="${listResult.size == 3}">
                    <option value="3" selected>3</option> <!-- 선택된 상태 -->
                    <option value="5">5</option>
                    <option value="10">10</option>
                </c:when>

                <!-- 만약 현재 listResult.size 값이 5라면 -->
                <c:when test="${listResult.size == 5}">
                    <option value="3">3</option>
                    <option value="5" selected>5</option> <!-- 선택된 상태 -->
                    <option value="10">10</option>
                </c:when>

                <!-- 만약 현재 listResult.size 값이 10이라면 -->
                <c:when test="${listResult.size == 10}">
                    <option value="3">3</option>
                    <option value="5">5</option>
                    <option value="10" selected>10</option> <!-- 선택된 상태 -->
                </c:when>
            </c:choose>
        </select>

        <script language="javascript">
            // 드롭다운에서 페이지 크기(ps) 선택 시 실행되는 함수
            function f(select){
                // 선택된 option의 value값 가져오기 (3, 5, 10 중 하나)
                var ps = select.value;

                //alert("ps : " + ps); // 디버깅용 (선택값 확인용)

                // 선택된 값으로 list.do 요청 보내기
                // URL 예: list.do?size=5
                location.href = "list.do?size=" + ps;
            }
        </script>


    </font>
    <hr width='600' size='2' color='gray' noshade>
</center>
</body>
</html>