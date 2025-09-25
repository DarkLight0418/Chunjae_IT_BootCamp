<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> <!--JSTL 3.0 -->
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<style>
    a{text-decoration:none}
</style>
<body style="text-align:center">
<h2>HJ SpringAI ( index.jsp )</h2><br/>

<a href="openai/chat.do">Open AI 확인</a><br/><br/>
<a href="springai/chat.do">SpringAI Chat</a><br/>
<a href="springai/image.do">SpringAI Image</a><br/>
<a href="springai/speech.do">SpringAI Speech(TTA)</a><br/>
<a href="springai/transcription.do">SpringAI Transcription(ATT)</a><br/>
<a href="springai/embedding.do">SpringAI Embedding</a><br/>
<a href="springai/moderation.do">SpringAI Moderation</a><br/>
</body>
</html>