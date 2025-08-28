package addr.mvc.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import addr.mvc.domain.Address;
import java.io.*;
import addr.mvc.model.AddrService;
import java.util.*;

@WebServlet("/ajax/ajax.do")
public class AjaxAddrController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch (m) {
				case "form": form(request, response); break;
				case "search01": search01(request, response); break;
				case "search02": search02(request, response); break;
				case "auto_form": autoForm(request, response); break;
				default: form(request, response);
			}
		}else {
			form(request, response);
		}
	}
	
	private void form(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = "ajax.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private void search01(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String seqStr = request.getParameter("seq");
		long seq = checkSeq(seqStr); 
		
		String addressJson = "";
		if(seq != -1L) {
			AddrService service = AddrService.getInstance();
			Address address = service.selectBySeqS(seq);
			//System.out.println("AjaxAddrController address: " + address);
			//예) addressJson = "{\"seq\":74, \"name\":\"홍길동\", \"addr\":\"부산시\", \"rdate\":\"2025-08-19 11:17:55\"}";
			
			addressJson = "{\"seq\":" + address.getSeq()
			+", \"name\":\"" + address.getName()
			+"\", \"addr\":\"" + address.getAddr()
			+"\", \"rdate\":\"" + address.getRdate()
			+"\"}";
			System.out.println("#(1)addressJson: " + addressJson);
		}
		try {
			response.setContentType("application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.print(addressJson);
		}catch(IOException ie) {}
	}
	private void search02(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String na = request.getParameter("na");
		
		String addressJson = "[]";
		if(na != null && na.length() != 0) {
			na = na.trim();
			AddrService service = AddrService.getInstance();
			ArrayList<Address> list = service.selectByNameS(na);
			System.out.println("AjaxAddrController list: " + list);
			
			if(list.size() != 0) {
				addressJson = "[";
				for(Address address: list) {
					addressJson += "{\"seq\":" + address.getSeq()
					+", \"name\":\"" + address.getName()
					+"\", \"addr\":\"" + address.getAddr()
					+"\", \"rdate\":\"" + address.getRdate()
					+"\"}";
					
					addressJson += ",";
				}
				addressJson = addressJson.substring(0, addressJson.length()-1);
				addressJson += "]";
			}
			System.out.println("#(2)addressJson: " + addressJson);
		}
		
		try {
			response.setContentType("application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.print(addressJson);
		}catch(IOException ie) {}
	}
	
	private long checkSeq(String seqStr) {
		if(seqStr == null) {
			return -1L;
		}else {
			seqStr = seqStr.trim();
			if(seqStr.length() == 0) {
				return -1L;
			}else {
				try {
					long seq = Long.parseLong(seqStr);
					return seq;
				}catch(NumberFormatException ne) {
					return -1L;
				}
			}
		}
	}
	
	private void autoForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = "autocomplete.jsp";
		response.sendRedirect(view);
	}
	
}
