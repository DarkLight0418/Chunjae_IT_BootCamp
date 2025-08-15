package addr.mvc.control;

import java.io.IOException;
import java.util.ArrayList;

import addr.mvc.domain.Address;
import addr.mvc.model.AddrService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/address/addr.do")
public class AddrController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException { 
		String m = request.getParameter("m");
	
		if(m != null) {
			m = m.trim();
			switch(m) {
				case "input": input(request, response); break;
				case "insert": insert(request, response); break;
				case "del": del(request, response); break;
				default: list(request, response);
			}
		} else {
			list(request, response);
		}
	}
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		AddrService service = AddrService.getInstance();
		ArrayList<Address> list = service.listS();
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
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		Address dto = new Address(-1, name, addr, null);
		
		AddrService service = AddrService.getInstance();
		boolean flag = service.insertS(dto);
		request.setAttribute("flag", flag);
		request.setAttribute("kind", "insert");;
		
		String view = "msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void del(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String seqStr = request.getParameter("seq");
		long seq = Long.parseLong(seqStr);
		
		AddrService service = AddrService.getInstance();
		boolean flag = service.deleteS(seq);
		// response.sendRedirect("addr.do");  // 알림 없이
		
		request.setAttribute("flag", flag);
		request.setAttribute("kind", "del");
		
		String view = "msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
