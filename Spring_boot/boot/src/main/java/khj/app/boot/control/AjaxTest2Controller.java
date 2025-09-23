package khj.app.boot.control;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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


// ObjectMapper 이용해서

@AllArgsConstructor
@RequestMapping("ajax2")
@Controller
public class AjaxTest2Controller {
    private final AddressAjaxService addressAjaxService;
    private final ObjectMapper objectMapper;

    @GetMapping("search1.do")
    public void search1(@RequestParam("seq") long seq, HttpServletResponse response) {
        Address address = addressAjaxService.getBySeqS(seq);

        try {
            String addressJson = objectMapper.writeValueAsString(address);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.println(addressJson);
        }catch(JsonProcessingException je) {
        }catch(IOException ie) {}
    }

    @PostMapping("search2.do")
    public void search1(@RequestParam("name") String name, HttpServletResponse response) {
        if (name != null) name = name.trim();
        List<Address> list = addressAjaxService.getListByNameS(name);

        try {
            String addressJson = objectMapper.writeValueAsString(list);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.println(addressJson);
        }catch(JsonProcessingException je) {
        }catch(IOException ie) {}
    }
}
