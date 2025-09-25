<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Spring AI 임베딩</title>
    <meta charset="UTF-8">

    <!-- jQuery 라이브러리 로드 (CDN 방식) -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script>
        // 문서가 로드되고 DOM이 준비되면 실행됨 (= $(document).ready())
        $(function() {

            // "임베딩 생성" 버튼 클릭 시 실행되는 이벤트 핸들러
            $('#embeddingBtn').click(function() {

                // 입력된 텍스트 가져오기 (앞뒤 공백 제거)
                const inputText = $('#inputText').val().trim();

                // 입력값이 없을 경우 경고창 표시 후 실행 중단
                if (!inputText) {
                    alert("텍스트를 입력해 주세요.");
                    return;
                }

                // jQuery AJAX 요청
                $.ajax({
                    url: 'embedding.do',   // 서버로 요청을 보낼 URL (Spring Controller 매핑)
                    type: 'POST',          // HTTP 메서드
                    data: { text: inputText }, // 서버로 전송할 데이터 (key: text, value: inputText)
                    dataType: 'json',      // 서버에서 받을 응답 형식 (JSON)

                    // 요청 성공 시 실행되는 콜백 함수
                    success: function(response) {
                        console.log("응답:", response); // 전체 응답 객체 출력
                        console.log("텍스트:", response.text); // 서버에서 돌려준 텍스트 출력
                        console.log("임베딩:", response.embedding); // 서버에서 돌려준 임베딩 출력

                        // 결과를 화면에 표시
                        $('#originalText').text(response.text);           // 입력한 텍스트 출력
                        $('#embeddingResult').text(response.embedding);   // 임베딩 결과 출력
                        $('#resultArea').show();                          // 결과 영역 보이기
                    },

                    // 요청 실패 시 실행되는 콜백 함수
                    error: function(xhr, status, error) {
                        console.error("에러 발생:", status, error);
                        alert("임베딩 요청 중 오류가 발생했습니다.");
                    }
                });
            });
        });
    </script>
</head>
<body style="text-align: center">

<h2>Spring AI 텍스트 임베딩</h2>

<!-- 사용자 입력 영역 -->
<textarea id="inputText" rows="5" cols="100" placeholder="텍스트를 입력하세요"></textarea><br><br>
<button id="embeddingBtn">임베딩 생성</button>

<hr>

<!-- 결과 출력 영역 (처음에는 숨김 상태) -->
<div id="resultArea" style="display: none;">
    <h3>입력한 텍스트:</h3>
    <p id="originalText"></p>

    <h3>임베딩 결과:</h3>
    <!-- 임베딩 결과는 길어질 수 있으므로 textarea로 출력 -->
    <textarea id="embeddingResult" rows="100%" cols="100" style="overflow-y: auto;"></textarea>
</div>

</body>
</html>
