package soo.sv;

import jakarta.servlet.http.*; 
import jakarta.servlet.*; 
import java.io.*; 

public class GetServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,
		HttpServletResponse res) throws ServletException, IOException {
		//1. Take
		String name = req.getParameter("name");
		String ageStr = req.getParameter("age");
		int age = Integer.parseInt(ageStr);
		System.out.println("GetServlet을 이용해 서버측에서 받은 정보 name:"+name+", age:" + age);

		//2. Give 
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
		pw.println("<body style='text-align:center'>");
			pw.println("<h2>GetServlet의 응답</h2>");
			pw.println("<br/>");
			pw.println("<div style='color:red'>"+name+"("+age+")님 어솨~^^</div>");
			pw.println("<br/><br/>");
			pw.println("<a href='./' style='text-decoration:none'>인덱스</a>");
		pw.println("</body>");
	}
}
