package mvcBoardPJ.com.example.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String type = request.getParameter("type");
		if (type != null) {
			type = type.trim();
			switch(type) {
				case "login": goLogin(request, response); break;
				case "check": check(request, response); break;
				default: response.sendRedirect("index.jsp");
			}
		} else {
			response.sendRedirect("index.jsp");
		}
	}

	public void goLogin(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String login = "/login/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(login);
		rd.forward(request, response);
	}
	
	public void check(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		if (id == null || pwd == null) {
			request.setAttribute("errorMsg", "아이디 비밀번호를 입력하세요");
			RequestDispatcher rd = request.getRequestDispatcher("/login/login.jsp");
			rd.forward(request, response);
		}
		
		if (id.equals("dddd") && pwd.equals("111")) {
			String success = "/login/successLogin.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(success);
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/login/failLogin.jsp");
			rd.forward(request, response);
		}
	}
}
	
