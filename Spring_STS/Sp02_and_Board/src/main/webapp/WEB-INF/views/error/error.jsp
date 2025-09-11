<%@ page contentType="text/html;charset=UTF-8" %>
<h2>예외 발생!</h2>
<p>${ex}</p>
<pre>
<%
    Exception ex = (Exception) request.getAttribute("ex");
    if (ex != null) {
        ex.printStackTrace(new java.io.PrintWriter(out));
    }
%>
</pre>

