package soo.sv;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class HelloServlet extends HttpServlet {
	@Override
	public void service(HttpServletRequest req,
						HttpServletResponse res) throws ServletException, IOException {
		String m = req.getMethod();

		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();

		pw.println("<!DOCTYPE html>");
		pw.println("<html lang='en'>");
		pw.println("<head>");
		pw.println("<meta charset='UTF-8'>");
		pw.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		pw.println("<title>Document</title>");
		pw.println("</head>");
		pw.println("<body style='text-align:center'>");
		pw.println("<h2>안녕 서블릿(요청방식:"+m+")</h2>");
		pw.println("<br/><br/>");
		pw.println("<img src='./tomcat.svg'/>");
		pw.println("<br/>");
		pw.println("<img src='./imgs/헬로지피티5.png'>");
		pw.println("</body>");
		pw.println("</html>");
	}
}
