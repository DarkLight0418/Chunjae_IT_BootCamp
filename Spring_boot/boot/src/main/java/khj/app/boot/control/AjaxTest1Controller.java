package khj.app.boot.control;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import khj.app.boot.domain.Address;
import khj.app.boot.service.AddressAjaxService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


// JSON 문자열 담는 거
@AllArgsConstructor
@RequestMapping("ajax1")
@Controller
public class AjaxTest1Controller {
    private final AddressAjaxService addressAjaxService;

    @GetMapping("search1.do")
    public void search1(@RequestParam("seq") long seq, HttpServletResponse response){
        Address address = addressAjaxService.getBySeqS(seq);

        String addressJson = null;
        if(address != null) {
            addressJson = "{\"seq\":"+address.getSeq()
                    +", \"name\":\""+address.getName()
                    +"\", \"addr\":\""+address.getAddr()
                    +"\", \"rdate\":\""+address.getRdate()
                    +"\"}";
        }
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.println(addressJson);
        }catch(IOException ie) {}
    }

    @PostMapping("search2.do")
    public void search1(@RequestParam("name") String name, HttpServletResponse response){
        if(name != null) name = name.trim();
        List<Address> list = addressAjaxService.getListByNameS(name);

        String addressJson = null;
        if(list.size() != 0) {
            addressJson = "[";
            for(Address address: list) {
                addressJson += "{\"seq\":"+address.getSeq()
                        + ", \"name\":\""+address.getName()
                        +"\", \"addr\":\""+address.getAddr()
                        +"\", \"rdate\":\""+address.getRdate()
                        + "\"}";

                addressJson += ",";
            }
            addressJson = addressJson.substring(0, addressJson.length()-1);
            addressJson += "]";
        }else {
            addressJson = "[]";
        }

        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.println(addressJson);
        }catch(IOException ie) {}
    }
}
