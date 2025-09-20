package khj.app.boot.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import khj.app.boot.domain.Address;
import khj.app.boot.service.AddressService;
import java.util.List;

@RequestMapping("address")
@Controller
public class AddressController {
    private final AddressService addressService;
    @Autowired
    private AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("list.do")
    public String list(Model model){
        List<Address> list = addressService.listS();
        model.addAttribute("list", list);
        return "address/list";
    }
    @GetMapping("write.do")
    public String write(){
        return "address/write";
    }
    @PostMapping("write.do")
    public String write(Address address){
        Address addressDb = addressService.insertS(address);
        pln("@AddressController write() addressDb: " + addressDb);
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
