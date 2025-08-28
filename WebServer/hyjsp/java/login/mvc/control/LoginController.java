package login.mvc.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import login.mvc.domain.Member;
import login.mvc.model.LoginService;
import static login.mvc.model.LoginConst.*;

import java.io.IOException;


@WebServlet("/login/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch(m) {
				case "form": form(request, response); break;
				case "check": check(request, response); break;
				case "logout": logout(request, response); break;
			}
		}else {
			response.sendRedirect("../");
		}
	}

	public void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "login.jsp";
		response.sendRedirect(view);
	}
	
	public void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		//서버측 유효성 검사를 추가하는 것이 좋음(클라이언트:javaScript, 서버:java 컨트롤러)
		
		LoginService service = LoginService.getInstance();
		int result = service.check(email, pwd);
		System.out.println("@LoginController result: " + result);
		
		if (result == YES_ID_PWD) {
			Member m = service.getMemverS(email);
			HttpSession session = request.getSession();
			session.setAttribute("loginOkUser", m);
		}
		request.setAttribute("result", result);
		
		String view = "msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("loginOkUser");
		session.invalidate();
		
		String view = "login.do";
		response.sendRedirect(view);
	}
}
