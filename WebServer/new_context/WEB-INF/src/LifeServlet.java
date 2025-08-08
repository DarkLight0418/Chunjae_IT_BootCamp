package soo.sv;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class LifeServlet extends HttpServlet {
	@Override //첫번째 요청에의해 객체가 메모리에 생성될 때(로딩시) 호출
	public void init() throws ServletException {
		System.out.println("LifeServlet init()");
	}
	@Override //LifeServlet 요청시마다..
	public void service(HttpServletRequest req,
						HttpServletResponse res) throws ServletException, IOException {
		System.out.println("LifeServlet service()");
	}
	@Override //객체가 메모리에서 삭제될 때 (언로딩시) 호출: 안전파킹, 클래스갱신, 서버정책, ..
	public void destroy(){
		System.out.println("LifeServlet destroy()");
		try{
			FileWriter fw = new FileWriter("life.log");
			PrintWriter pw = new PrintWriter(fw, true);
			pw.println("LifeServlet destroy() 수행됨");
			pw.close();
			fw.close();
		}catch(IOException ie){}
	}
}

