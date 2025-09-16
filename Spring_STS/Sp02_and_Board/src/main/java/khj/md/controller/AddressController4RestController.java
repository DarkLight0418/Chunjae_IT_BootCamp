package khj.md.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("rest_addr")
@Controller
public class AddressController4RestController {
	@GetMapping("write.do")
	public String write() {
		return "rest_addr/write";
	}
}
