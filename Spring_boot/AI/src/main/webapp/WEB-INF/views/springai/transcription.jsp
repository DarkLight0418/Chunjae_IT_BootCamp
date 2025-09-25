<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>오디오 텍스트 변환</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#transcribeButton").click(function() {
                var formData = new FormData($("#transcriptionForm")[0]);
                $.ajax({
                    url: "transcription.do",
                    type: "POST",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function(response) {
                        $("#transcriptionText").text(response);
                        $("#errorMessage").text("");
                    },
                    error: function(xhr) {
                        $("#transcriptionText").text("");
                        $("#errorMessage").text("오류 발생: " + xhr.responseText);
                    }
                });
            });
        });
    </script>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 40px auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
            font-size: 1.8em;
            margin-bottom: 30px;
        }

        h2 {
            font-size: 1.3em;
            margin-bottom: 10px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 10px;
        }

        input[type="file"] {
            display: block;
            width: 100%;
            padding: 10px;
            font-size: 1em;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 6px;
        }

        button {
            padding: 12px 20px;
            font-size: 1em;
            color: white;
            background-color: #4CAF50;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #45a049;
        }

        .result {
            margin-top: 30px;
            background-color: #fff7e6;
            border: 1px solid #ffe0b2;
            padding: 20px;
            border-radius: 10px;
        }

        #transcriptionText {
            white-space: pre-wrap;
            font-size: 1.1em;
        }

        .error {
            color: red;
            font-weight: bold;
            margin-top: 10px;
        }

        @media (max-width: 600px) {
            .container {
                margin: 20px;
                padding: 20px;
            }

            h1 {
                font-size: 1.5em;
            }

            h2 {
                font-size: 1.1em;
            }

            button {
                width: 100%;
                font-size: 1em;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h1>🎧 오디오 → 텍스트 변환기</h1>

    <h2>🎵 오디오 업로드</h2>
    <form id="transcriptionForm" enctype="multipart/form-data">
        <label for="audioFile">오디오 파일 선택:</label>
        <input type="file" id="audioFile" name="audioFile" accept="audio/*" required>
        <button type="button" id="transcribeButton">변환 시작</button>
    </form>

    <div class="result">
        <h2>📝 변환 결과</h2>
        <p id="transcriptionText"></p>
        <p id="errorMessage" class="error"></p>
    </div>
</div>
</body>
</html>