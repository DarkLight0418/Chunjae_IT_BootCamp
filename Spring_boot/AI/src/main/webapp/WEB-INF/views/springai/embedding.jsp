<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Spring AI 임베딩</title>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script> <!--추가-->
    <script>
        let chartInstance = null; //추가

        $(function() {
            $('#embeddingBtn').click(function() {
                const inputText = $('#inputText').val().trim();
                if (!inputText) {
                    alert("텍스트를 입력해 주세요.");
                    return;
                }

                $.ajax({
                    url: 'embedding.do',
                    type: 'POST',
                    data: { text: inputText },
                    dataType: 'json', // 💡 이 줄이 중요합니다!
                    success: function(response) {
                        console.log("응답:", response);
                        console.log("텍스트:", response.text);
                        console.log("임베딩:", response.embeddings);

                        // 추가시작
                        // 텍스트 및 원시 벡터 출력
                        $('#originalText').text(response.text);
                        $('#embeddingResult').val(response.embeddings.join(', ')); // textarea에 표시

                        // Chart.js로 시각화
                        const ctx = document.getElementById('embeddingChart').getContext('2d');

                        // 기존 차트 제거 (중복 방지)
                        if (chartInstance) {
                            chartInstance.destroy();
                        }

                        // 새 차트 생성
                        chartInstance = new Chart(ctx, {
                            type: 'line',
                            data: {
                                labels: response.embeddings.map((_, i) => i), // X축: 인덱스
                                datasets: [{
                                    label: 'Embedding 값',
                                    data: response.embeddings,
                                    borderColor: 'rgba(54, 162, 235, 1)',
                                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                                    borderWidth: 1,
                                    pointRadius: 0,
                                    tension: 0.2
                                }]
                            },
                            options: {
                                scales: {
                                    x: {
                                        display: true,
                                        title: {
                                            display: true,
                                            text: '차원 인덱스'
                                        }
                                    },
                                    y: {
                                        display: true,
                                        title: {
                                            display: true,
                                            text: '값'
                                        }
                                    }
                                },
                                plugins: {
                                    legend: {
                                        display: false
                                    }
                                }
                            }
                        });

                        $('#resultArea').show();
                        //추가끝
                    },
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

<textarea id="inputText" rows="5" cols="100" placeholder="텍스트를 입력하세요"></textarea><br><br>
<button id="embeddingBtn">임베딩 생성</button>

<hr>
<div id="resultArea" style="display: none;">
    <h3>입력한 텍스트:</h3>
    <p id="originalText"></p>

    <h3>임베딩 결과:</h3>
    <textarea id="embeddingResult" rows="100%" cols="100" style="overflow-y: auto;"></textarea>

    <h3>임베딩 시각화 (그래프)</h3> <!--추가-->
    <canvas id="embeddingChart" height="150"></canvas>
</div>

</body>
</html>