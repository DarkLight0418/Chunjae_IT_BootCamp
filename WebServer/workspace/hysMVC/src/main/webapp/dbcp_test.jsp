<%@ page contentType="text/html;charset=utf-8" 
import="javax.sql.DataSource, java.sql.Connection, java.sql.SQLException"%>
<jsp:useBean id="dbcp" class="young.dbcp.DbcpBean" scope="application" />

<%

	try (Connection con = dbcp.getDs().getConnection()) {
	        out.println("생성된 MariaDB의 con : " + con.hashCode());
    } catch (SQLException e) {
        e.printStackTrace(); // 톰캣 로그
        out.println("<pre>SQLException: " + e + "</pre>");
    } catch (Throwable t) {
        t.printStackTrace();
        out.println("<pre>Other: " + t + "</pre>");
    }
   
%>

<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DBCP 테스트</title>
	<style>
		a {text-decoration:none; }
		p {font-size: 24px;}
	</style>
</head>
<body>
	<p>DBCP</p>
</body>
</html>

<%-->

<%
	DataSource ds = dbcp.getDs();
	Connection con = ds.getConnection();
%>
생성된 MariaDB의 con  : <%=con.hashCode()%>

<%
    Context initContext = new InitialContext();
    Context envContext = (Context)initContext.lookup("java:comp/env");
    ds = (DataSource)envContext.lookup("jdbc/TestDB");
    con = ds.getConnection();
    
    out.println("<p>driver=" + org.apache.commons.lang3.ObjectUtils.defaultIfNull(
    request.getServletContext().getInitParameter("driverClassName"), "n/a") + "</p>");
	out.println("<p>ds class=" + (ds==null?"null":ds.getClass().getName()) + "</p>");
	try (Connection con = ds.getConnection()) {
	    out.println("OK: " + con.hashCode());
	} catch (Throwable t) {
	    t.printStackTrace(); // 톰캣 로그
	    out.println("<pre>" + t + "</pre>");
%>

--%>