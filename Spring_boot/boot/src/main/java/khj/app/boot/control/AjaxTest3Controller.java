package khj.app.boot.control;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import khj.app.boot.domain.Address;
import khj.app.boot.service.AddressAjaxService;
import java.util.List;


// ResponseBody 이용한 방법
@AllArgsConstructor
@RequestMapping("ajax3")
@Controller
public class AjaxTest3Controller {
    private final AddressAjaxService addressAjaxService;

    @GetMapping("search1.do")
    public @ResponseBody Address search1(@RequestParam("seq") long seq) {
        Address address = addressAjaxService.getBySeqS(seq);
        return address;
    }
    @PostMapping("search2.do")
    public @ResponseBody List<Address> search1(@RequestParam("name") String name) {
        if (name != null) name = name.trim();
        List<Address> list = addressAjaxService.getListByNameS(name);
        return list;
    }

    @GetMapping(value="txt", produces = "application/json;charset=utf-8")
    public @ResponseBody String txt() {
        return "안녕하세요";
    }
    //http://127.0.0.1:8080/ajax3/txt
}
