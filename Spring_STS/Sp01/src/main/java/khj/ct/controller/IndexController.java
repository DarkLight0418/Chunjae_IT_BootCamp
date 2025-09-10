package khj.ct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";  // /WEB-INF/views/"index".jsp  index만 리턴해도 반복되는 부분은 다 알아서 스프링으로 커버 가능
	}
}
