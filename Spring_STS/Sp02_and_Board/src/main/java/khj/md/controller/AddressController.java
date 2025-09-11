package khj.md.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import khj.md.domain.Address;
import khj.md.service.AddressService;

@AllArgsConstructor
@Controller
@RequestMapping("address")
public class AddressController {
	private AddressService addressService; //Spring 4.3~ : AutoInjection( with @AllArgsConstructor )
	
	@GetMapping("list.do")  // 밑과 같음
	// @RequestMapping( value = "list.do", method = RequestMethod.GET)
	public ModelAndView list() {  // 데이터 리스팅도 같이 해줘야 함
		
		
		/* List<Address> list = addressService.listS();
	
		ModelAndView mv = new ModelAndView(); //V
		mv.setViewName("address/list"); //M
		mv.addObject("list", list);
		*/
		
		return new ModelAndView("address/list", "list", addressService.listS());
	}
	
	// 작성 폼 이동
	@GetMapping("write.do")
	public ModelAndView write(Address address) {
		return new ModelAndView("address/write", "address", new Address());
	}
	
	/*
	@GetMapping("write.do")
	public String write() {
		return "address/write";
	}
	*/
	
	// 작성 처리
	@PostMapping("write.do")
	public String insert(Address address) {
		addressService.insertS(address);
		return "redirect:/address/list.do";
		// return "list"  
		// 이렇게도 쓸 수 있으나 바람직하지 않음. (view만 호출) -> 이럴 땐 redirect 방식이 적합함.
	}
	
	// 이동하는 로직과 처리하는 메소드로 나눠서 작업하면 됨.
	
	/*
	@GetMapping("del.do")
	public String delete(long seq) {
		addressService.deleteS(seq);
		return "redirect:/address/list.do";
	}
	*/
	
	@GetMapping("del.do")
	public String delete(long seq, HttpSession session, HttpServletRequest request, 
			Object page, HttpServletRequest response) {  
		/* 파라미터로 스코프를 생성하면, 스프링이 알아서 주입해줌 
		(ServletContext 어플리케이션은 안되는 듯)
		
		아래와 같이 세션에서 접근
		*/
		ServletContext application = session.getServletContext();
		
		System.out.println("@request : " + request);
		System.out.println("@application : " + application);
		System.out.println("@session : " + session);
		System.out.println("@page : " + page);
		System.out.println("@@response : " + response);
		
		addressService.deleteS(seq);
		return "redirect:/address/list.do";
		
		/*
		@GetMapping("del.do")
		public String delete(long seq, 
			HttpSession session, HttpServletRequest request, Object page, HttpServletResponse response) {
			ServletContext application = session.getServletContext();
			pln("@application: " + application);
			pln("@session: " + session);
			pln("@request: " + request);
			pln("@page: " + page);
			pln("@@response: " + response);
			
			addressService.deleteS(seq);
			return "redirect:list.do";
		}
		*/
		
	}
}
