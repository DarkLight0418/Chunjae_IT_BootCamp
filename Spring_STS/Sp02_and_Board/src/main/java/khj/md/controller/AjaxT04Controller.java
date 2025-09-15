package khj.md.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import khj.md.domain.Address;
import khj.md.service.AddressAjaxService;

@AllArgsConstructor
@RequestMapping("ajax04")
@Controller
@RestController  // 템플릿 리턴을 못함, '데이터'만 가능 <-> @ResponseBody는 템플릿도 가능
public class AjaxT04Controller {
	private AddressAjaxService addressAjaxService;
	
	@GetMapping(value="search01", produces={MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Address search01(long seq) {
        return addressAjaxService.selectBySeqS(seq);
    }
    //http://127.0.0.1:8080/ajax04/search01.json?seq=74
    //http://127.0.0.1:8080/ajax04/search01.xml?seq=74  // 태그가 필요할 수도 있음
    //http://127.0.0.1:8080/ajax04/search01?seq=74
    @PostMapping(value="search02", produces={MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Address> search02(String name) {
        return addressAjaxService.selectByNameS(name);
    }

    @GetMapping(value="txt", produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String txt() {
        return "안녕하세요";
    }
    //http://127.0.0.1:8080/ajax04/txt.json
    //http://127.0.0.1:8080/ajax04/txt
    
    void pln(String str) {
        System.out.println(str);
    }
}