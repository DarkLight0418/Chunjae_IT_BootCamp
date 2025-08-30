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
		
		if (type != null) {
			 type = type.trim();
			switch (type) {
				case "qna" : qna(request, response); break;
				case "knowledge" : knowledge(request, response); break; 
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
	
	private void knowledge(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String kSPage = "/WEB-INF/board/knowledgeShareBoard.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(kSPage);
		rd.forward(request, response);
	}
}