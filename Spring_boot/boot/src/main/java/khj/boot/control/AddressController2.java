package khj.boot.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import khj.boot.domain.Address;
import khj.boot.service.AddressService;
import khj.boot.service.AddressService2;

import java.util.List;

@RequestMapping("address2")
@Controller
public class AddressController2 {
    private final AddressService2 addressService;
    @Autowired
    private AddressController2(AddressService2 addressService){
        this.addressService = addressService;
    }

    @GetMapping("list.do")
    public String list(Model model){
        List<Address> list = addressService.listS();
        model.addAttribute("list", list);
        return "address2/list";
    }
    @GetMapping("write.do")
    public String write(){
        return "address2/write";
    }
    @PostMapping("write.do")
    public String write(Address address){
        boolean flag = addressService.insertS(address);
        return "redirect:list.do";
    }
    @GetMapping("del.do")
    public String del(@RequestParam("seq") long seq){
        boolean flag = addressService.deleteS(seq);
        return "redirect:list.do";
    }

    void pln(String str){
        System.out.println(str);
    }
}
