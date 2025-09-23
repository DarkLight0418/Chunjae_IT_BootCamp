package khj.app.boot.control;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import khj.app.boot.domain.Address;
import khj.app.boot.service.AddressAjaxService;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)  // 다른 서버에서도 사용할 수 있게 보안 처리하는 거인듯

@AllArgsConstructor
@RequestMapping("ajax4")
@RestController
public class AjaxTest4Controller {
    private final AddressAjaxService addressAjaxService;

    @GetMapping("search1")
    public Address search1(@RequestParam("seq") long seq) {
        Address address = addressAjaxService.getBySeqS(seq);
        return address;
    }
    @PostMapping("search2")
    public List<Address> search2(@RequestParam("name") String name) {
        if (name != null) name = name.trim();
        List<Address> list = addressAjaxService.getListByNameS(name);
        return list;
    }

    @GetMapping("txt")
    public String txt() {
        return "안녕하세요";
    }
    //http://127.0.0.1:8080/ajax4/txt
}
