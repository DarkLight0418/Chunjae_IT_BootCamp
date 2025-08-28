<%@ page contentType="text/html;charset=utf-8" 
import="javax.sql.DataSource, java.sql.Connection"%>
<jsp:useBean id="dbcp" class="young.dbcp.DbcpBean" scope="application" />
<%
	DataSource ds = dbcp.getDs();
	Connection con = ds.getConnection();
%>
생성된 MariaDB의 con  : <%=con.hashCode()%>

<%--
<%
    Context initContext = new InitialContext();
    Context envContext = (Context)initContext.lookup("java:/comp/env");
    DataSource ds = (DataSource)envContext.lookup("jdbc/TestDB");
    Connection con = ds.getConnection();
%>
생성된 MariaDB의 con  : <%=con.hashCode()%>
--%>