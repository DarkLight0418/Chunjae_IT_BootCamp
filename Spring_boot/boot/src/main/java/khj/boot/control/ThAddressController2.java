package khj.boot.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import khj.boot.domain.Address;
import khj.boot.service.AddressService2;
import java.util.List;

@RequestMapping("th/address2")
@Controller
public class ThAddressController2 {
    @Autowired
    private AddressService2 addressService2;

    @GetMapping("list")
    public String list(Model model){
        System.out.println("@ThAddressController2 list() 호출");
        List<Address> list = addressService2.listS();
        model.addAttribute("list", list);
        return "th/address2/list";
    }
    @GetMapping("write")
    public String write(){
        return "th/address2/write";
    }
    @PostMapping("write")
    public String write(Address address){
        addressService2.insertS(address);
        return "redirect:list";
    }
    @GetMapping("del")
    public String delete(@RequestParam("seq") long seq){
        addressService2.deleteS(seq);
        return "redirect:list";
    }
}
