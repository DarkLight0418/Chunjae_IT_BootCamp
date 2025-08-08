package soo.sv;

import jakarta.servlet.http.*; 
import jakarta.servlet.*; 
import java.io.*; 

public class PostServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,
		HttpServletResponse res) throws ServletException, IOException {
		//1. Take
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		System.out.println("Post방식으로 서버측에서 받은 정보 id:"+id+", pwd:" + pwd);

		//2. Give 
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
		pw.println("<body style='text-align:center'>");
			pw.println("<h2>PostServlet의 응답</h2>");
			pw.println("<p>로긴 성공!</p>");
			pw.println("<div style='color:red'>"+id+"님 어솨~^^</div>");
			pw.println("<br/><br/>");
			pw.println("<a href='./' style='text-decoration:none'>인덱스</a>");
		pw.println("</body>");
	}
}