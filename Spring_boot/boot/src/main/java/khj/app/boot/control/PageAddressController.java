package khj.app.boot.control;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import khj.app.boot.domain.Address;
import khj.app.boot.dto.AddressListResult;
import khj.app.boot.service.PageAddressService;

@RequestMapping("address_page")
//@AllArgsConstructor
@RequiredArgsConstructor
@Controller
public class PageAddressController {
    private final PageAddressService pageAddressService;

    @GetMapping("list.do")
    public String list(@PageableDefault(size=3, sort="seq", direction = Sort.Direction.DESC)  Pageable pageable,
                       Model model){
        AddressListResult listResult = pageAddressService.getAddressListResult(pageable);
        model.addAttribute("listResult", listResult);
        return "/address_page/list";
    }
    @GetMapping("write.do")
    public String write(){
        return "/address_page/write";
    }
    @PostMapping("write.do")
    public String write(Address address){
        pageAddressService.insertS(address);
        return "redirect:list.do";
    }
    @GetMapping("del.do")
    public String delete(@RequestParam("seq") long seq,
                         ServletContext application, HttpSession session, HttpServletRequest request, Object object){
        //pln("@@application: " + application); //Auto Injection불가!!
        application = session.getServletContext();
        pln("@application: " + application);

        pageAddressService.deleteS(seq);
        return "redirect:list.do";
    }

    void pln(String str){
        System.out.println(str);
    }
}
