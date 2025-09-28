<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>주소 입력</title>
    <style>
        body {
            font-family: "맑은 고딕", sans-serif;
        }
        .container {
            text-align: center;
            margin-top: 50px;
        }
        table {
            border: 1px solid #333;
            border-collapse: collapse;
            width: 300px;
            margin: 0 auto;
        }
        th, td {
            border: 1px solid #333;
            padding: 8px;
            text-align: center;
        }
        h1 {
            margin-bottom: 20px;
        }
        input[type="text"] {
            width: 90%;
            padding: 5px;
        }
        input[type="submit"], input[type="reset"] {
            padding: 6px 12px;
            margin: 5px;
            cursor: pointer;
        }
    </style>
    <script src="../js/trim.js"></script>
    <script>
        function validateForm(event) {
            const form = event.target;
            const name = form.name.value.trim();
            const addr = form.addr.value.trim();

            if (!name) {
                alert("이름을 입력해주세요.");
                form.name.focus();
                event.preventDefault();
                return false;
            }
            if (getByteLen(name) > 10) {
                alert("이름이 너무 깁니다. (최대 10바이트)");
                form.name.focus();
                event.preventDefault();
                return false;
            }

            if (!addr) {
                alert("주소를 입력해주세요.");
                form.addr.focus();
                event.preventDefault();
                return false;
            }
            if (getByteLen(addr) > 20) {
                alert("주소가 너무 깁니다. (최대 20바이트)");
                form.addr.focus();
                event.preventDefault();
                return false;
            }
        }

        function getByteLen(str) {
            return str.replace(/[\0-\x7f]|([0-\u07ff]|(.))/g, "$&$1$2").length;
        }
    </script>
</head>
<body>
<div class="container">
    <h1>Address Input with SpringBoot</h1>
    <form name="f" action="write.do" method="post" onsubmit="validateForm(event)">
        <table>
            <tr>
                <td colspan="2"><h2>입력폼</h2></td>
            </tr>
            <tr>
                <th>이름</th>
                <td><input type="text" name="name" maxlength="10" required></td>
            </tr>
            <tr>
                <th>주소</th>
                <td><input type="text" name="addr" maxlength="20" required></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="전송">
                    <input type="reset" value="취소">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
