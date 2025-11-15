package khj.backend.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import khj.backend.domain.Readdress;
import khj.backend.domain.Reuser;
import khj.backend.dto.ReaddressRequest;
import khj.backend.repository.ReaddressRepository;
import khj.backend.repository.ReuserRepository;
import java.util.Optional;

@RestController
public class ReaddressController {
    @Autowired
    private ReaddressRepository readdressRepository;
    @GetMapping("/addrs") //수정
    public Iterable<Readdress> getReaddresses() {
        return readdressRepository.findAll();
    }

    @Autowired
    private ReuserRepository reuserRepository;
    @PostMapping("/addrs")
    public Readdress createAddress(@RequestBody ReaddressRequest readdressRequest) {
        String zip = readdressRequest.getZip();
        String addr = readdressRequest.getAddr();
        String username = readdressRequest.getUsername();
        System.out.println("@ReaddressController zip: " + zip + ", addr: " + addr + ", username: " + username);

        Readdress readdress = new Readdress();
        readdress.setZip(zip);
        readdress.setAddr(addr);
        Optional<Reuser> optionalReuser = reuserRepository.findByUsername(username);
        if(optionalReuser.isPresent()) {
            Reuser reuser = optionalReuser.get();
            readdress.setReuser(reuser);
        }
        return readdressRepository.save(readdress);
    }
}
