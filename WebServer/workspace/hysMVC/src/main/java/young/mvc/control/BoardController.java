package young.mvc.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import young.mvc.domain.*;
import young.mvc.model.BoardService;
import file.mvc.fileset.Path;
import file.mvc.model.FileService;


@WebServlet("/file-board/board.do")
@MultipartConfig(
		fileSizeThreshold = 10*1024*1024, //파일을 RAM에 보관할 수 있는 최대 크기( 10M초과면 디스크 저장 )
		maxFileSize = 20*1024*1024, //최대 파일 크기 
		maxRequestSize = 50*1024*1024 //요청당 최대 파일 크기
	)
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch(m) {
				case "input": input(request, response); break;
				case "insert": insert(request, response); break;
				case "show": show(request, response); break;
				case "del": delete(request, response); break;
				case "update": update(request, response); break;
				case "download": download(request, response); break;
				default: list(request, response);
			}
		}else {
			list(request, response);
		}
	}
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		ArrayList<Board> list = service.listS();
		request.setAttribute("list", list);
		
		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void input(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.sendRedirect("input.jsp");
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	    String writer = request.getParameter("writer");
	    String email = request.getParameter("email");
	    String subject = request.getParameter("subject");
	    String content = request.getParameter("content");
	    Part filePart = request.getPart("file");
	    String fname = filePart.getSubmittedFileName();
	    
	    if (fname == "" || fname == null) fname = null;
		
		BoardService service = BoardService.getInstance();
		FileService fileService = FileService.getInstance();
		
		String ofname = fileService.saveFile(filePart);
		
		Board dto = new Board(-1, writer, email, subject, content, fname, ofname, null);
		
		boolean flag = service.insertS(dto);
		request.setAttribute("flag", flag);
		request.setAttribute("kind", "insert");
		
		String view = "msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void show(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String seqStr = request.getParameter("seq");
		String edit = request.getParameter("edit");
		if(edit != null) {
			long seq = Long.parseLong(seqStr);
			
			BoardService service = BoardService.getInstance();
			Board dto = service.showS(seq);
			
			request.setAttribute("dto", dto);
			
			RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
			rd.forward(request, response);
		}else {
			long seq = Long.parseLong(seqStr);
			
			BoardService service = BoardService.getInstance();
			Board dto = service.showS(seq);
			
			request.setAttribute("dto", dto);
			
			RequestDispatcher rd = request.getRequestDispatcher("show.jsp");
			rd.forward(request, response);
		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String seqStr = request.getParameter("seq");
		long seq = Long.parseLong(seqStr);
				
		BoardService service = BoardService.getInstance();
		Board dto = service.showS(seq);
		
		if (dto.getOfname() != null) {
			File f = new File(Path.FILE_STORE, dto.getOfname());
			if(f.exists()) f.delete();
		}
		
		boolean flag = service.deleteS(seq);
		
		request.setAttribute("flag", flag);
		request.setAttribute("kind", "delete");
		
		String view = "msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	    String seqStr = request.getParameter("seq");
	    long seq = Long.parseLong(seqStr);
	    String writer = request.getParameter("writer");
	    String email = request.getParameter("email");
	    String subject = request.getParameter("subject");
	    String content = request.getParameter("content");
	    Part filePart = request.getPart("file");
	    String fname = filePart.getSubmittedFileName();
		String ofname ="";
	    
	    if (fname == null || fname.trim().equals("")) {
	        fname = null;
	    }
	    
		BoardService service = BoardService.getInstance();
		FileService fileService = FileService.getInstance();
		Board dto = service.showS(seq);

		
		if (fname != null) {
			File f = new File(Path.FILE_STORE, dto.getOfname());
			if(f.exists()) f.delete();
			ofname = fileService.saveFile(filePart);
		} else {
			fname = dto.getFname();
			ofname = dto.getOfname();
		}
		
		boolean flag = service.updateS(seq, writer, email, subject, content, fname, ofname);
		request.setAttribute("flag", flag);
		request.setAttribute("kind", "update");
		
		String view = "msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void download(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String seqStr = request.getParameter("seq");
	    long seq = Long.parseLong(seqStr);
	    
		BoardService service = BoardService.getInstance();
		Board dto = service.showS(seq);
	    
		String ofname = dto.getOfname();
		
        File f = new File(Path.FILE_STORE, dto.getOfname());

        FileService fileService = FileService.getInstance();
        fileService.download(request, response, f, dto.getFname());
    }
}
