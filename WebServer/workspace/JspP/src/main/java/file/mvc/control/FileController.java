package file.mvc.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import file.mvc.model.FileService;
import file.mvc.fileset.Path;
import java.io.*;

@WebServlet("/file/file.do")
@MultipartConfig(
	fileSizeThreshold = 10*1024*1024, //파일을 RAM에 보관할 수 있는 최대 크기( 10M초과면 디스크 저장 )
	maxFileSize = 20*1024*1024, //최대 파일 크기 
	maxRequestSize = 50*1024*1024 //요청당 최대 파일 크기
) 
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch(m) {
				case "form": form(request, response); break;
				case "upload": upload(request, response); break;
				case "form_mt": form_mt(request, response); break;
				case "upload_mt": upload_mt(request, response); break;
				case "list": list(request, response); break;
				case "del": del(request, response); break;
				case "download": download(request, response); break;
			}
		}else {
			list(request, response);
		}
	}
	private void form(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.sendRedirect("form.jsp");
	}
	private void upload(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String name = request.getParameter("name");
		Part filePart = request.getPart("file");
		//System.out.println("@name: " + name + ", filePart: " + filePart);
		
		FileService service = FileService.getInstance();
		boolean flag = service.saveFile(filePart);
		if(flag) {
			System.out.println("업로드 성공");
		}else {
			System.out.println("업로드 실패");
		}
		
		response.sendRedirect("file.do");
	}
	private void form_mt(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.sendRedirect("form_mt.jsp");
	}
	private void upload_mt(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		FileService service = FileService.getInstance();
		
		Collection<Part> col = request.getParts();
		for(Part filePart: col) {
			service.saveFile(filePart);
		}
		System.out.println("모두 업로드 성공");
		
		response.sendRedirect("file.do");
	}
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String uploadPath = Path.FILE_STORE;
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) uploadDir.mkdirs();
		
		File files[] = uploadDir.listFiles();
		request.setAttribute("files", files);
		
		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void del(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String fname = request.getParameter("fname");
		File f = new File(Path.FILE_STORE, fname);
		if(f.exists()) f.delete();
		
		response.sendRedirect("file.do");
	}
	private void download(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String fname = request.getParameter("fname");
		File f = new File(Path.FILE_STORE, fname);
		
		FileService service = FileService.getInstance();
		service.download(request, response, f);
	}
}
