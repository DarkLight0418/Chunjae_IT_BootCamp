package khj.md.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.AllArgsConstructor;
import khj.md.domain.Address;
import khj.md.service.AddressAjaxService;

@AllArgsConstructor
@RequestMapping("ajax02")
@Controller
public class AjaxT02Controller {
	private AddressAjaxService addressAjaxService;
	
	@GetMapping("search01.do")
	public void search01(long seq, HttpServletResponse response) {
		Address address = addressAjaxService.selectBySeqS(seq);
		
		ObjectMapper om = new ObjectMapper();
		try {
			String addressJson = om.writeValueAsString(address);
			response.setContentType("application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(addressJson);
		}catch(JsonProcessingException je) {
		}catch(IOException ie) {}
	}
	@PostMapping("search02.do")
	public void search02(String name, HttpServletResponse response) {
		if(name != null) name = name.trim();
		List<Address> list = addressAjaxService.selectByNameS(name);
		
		ObjectMapper om = new ObjectMapper();
		try {
			String addressJson = om.writeValueAsString(list);
			response.setContentType("application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(addressJson);
		}catch(JsonProcessingException je) {
		}catch(IOException ie) {}
	}
	
	void pln(String str) {
		System.out.println(str);
	}
}
