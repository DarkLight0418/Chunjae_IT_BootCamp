package khj.md.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.AllArgsConstructor;
import khj.md.domain.Address;
import khj.md.service.AddressAjaxService;

@AllArgsConstructor
@RequestMapping("ajax03")
@Controller
public class AjaxT03Controller {
	private AddressAjaxService addressAjaxService;
	
	@GetMapping("search01")
	public @ResponseBody Address search01(long seq) {
		//Address address = addressAjaxService.selectBySeqS(seq);
		return addressAjaxService.selectBySeqS(seq);
	}
	@PostMapping("search02")
	public @ResponseBody List<Address> search02(String name) {
		//if(name != null) name = name.trim();
		//List<Address> list = addressAjaxService.selectByNameS(name);
		
		return addressAjaxService.selectByNameS(name);
	}
	
	@GetMapping(value="txt", produces = "application/json;charset=utf-8")
	public @ResponseBody String txt() {
		return "안녕하세요";
	}
	//http://127.0.0.1:8080/ajax03/txt
	//http://127.0.0.1:8080/ajax03/txt.json
	
	void pln(String str) {
		System.out.println(str);
	}
}

