package board.mvc.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import board.mvc.domain.Board;
import board.mvc.model.BoardService;


@WebServlet("/mvcboard/board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String seqStr = request.getParameter("seq");
		
		if (m == null) {
			forwardToList(request, response);
			return;
		}
		
		switch (m.trim()) {
			case "input":
				forward(request, response, "mvcboard/input.jsp");
				break;
				
			case "insert": {
				String writer = request.getParameter("writer");
				String email = request.getParameter("email");
				String subject = request.getParameter("subject");
			}
			case "delete": {
				if (seqStr != null) {
					long seq = Long.parseLong(seqStr);
					BoardService.getInstance().delete(seq);
				}
				response.sendRedirect("board.do");
				break;
			}
			case "updateContent": {
				response.getWriter().println("<h1>updateContent() 실행됨</h1>");
				break;
			}
			
			case "doUpdate": {
				long seq = Long.parseLong(request.getParameter("seq"));
				String email = request.getParameter("email");
				String subject = request.getParameter("subject");
				String content = request.getParameter("content");
				
				Board updateDto = new Board();
				
				updateDto.setSeq(seq);
				updateDto.setEmail(email);
				updateDto.setSubject(subject);
				updateDto.setContent(content);
				
				BoardService.getInstance().update(updateDto);
				response.sendRedirect("board.do?m=view&seq=" + seq);
				}
				
			case "view": {
				if (seqStr != null) {
					long seq = Long.parseLong(seqStr);
					
					Board dto = new Board();
					dto.setSeq(seq);
					
					Board result = BoardService.getInstance().content(dto);
					
					request.setAttribute("board", result);
					forward(request, response, "/mvcboard/content.jsp");
				} else {
					response.sendRedirect("board.do");
				}
				break;
			}
			default:
				forwardToList(request, response);
		}
	}
	
	private void forwardToList(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Board> list = BoardService.getInstance().list();
		request.setAttribute("list", list);
		forward(request, response, "/mvcboard/list.jsp");
	}
			
	private void forward(HttpServletRequest request, 
			HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
