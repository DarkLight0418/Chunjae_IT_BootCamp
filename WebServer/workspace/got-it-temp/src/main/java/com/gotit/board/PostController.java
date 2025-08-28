package com.gotit.board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/board.do")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");  // 게시판 이름
		String action = request.getParameter("action");
		
		if (action != null) {
			action = action.trim();
			switch (action) {
				case "qna" :	qna(request, response); break;
				default: response.sendRedirect("../");
			}
		}
	}
	
	private void qna(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String qnaPage = "/WEB-INF/board/qnaBoard.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(qnaPage);
		rd.forward(request, response);
	}
}