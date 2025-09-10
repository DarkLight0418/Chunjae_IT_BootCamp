package khj.md.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@GetMapping("del.do")
	public String delete(long seq) {
		addressService.deleteS(seq);
		return "address/list";
	}
	
	@GetMapping("write.do")
	public String write(Address address) {
		return "address/write";
	}
}
