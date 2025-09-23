package khj.app.boot.control;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import khj.app.boot.domain.Address;
import khj.app.boot.service.AddressAjaxService;
import java.util.List;

@AllArgsConstructor
@RequestMapping("auto")
@Controller
public class AutoController {
    private final AddressAjaxService addressAjaxService;

    @GetMapping("auto.do")
    public String showView(){
        return "auto/autocomplete";
    }
    @PostMapping("auto.do")
    public @ResponseBody List<Address> getAddressList(String writer){
        if (writer != null) writer = writer.trim();
        List<Address> list = addressAjaxService.getListByNameS(writer);
        return list;
    }
}
