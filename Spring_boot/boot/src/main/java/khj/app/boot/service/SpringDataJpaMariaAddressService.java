package khj.app.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import khj.app.boot.domain.Address;
import khj.app.boot.repository.SpringDataJpaMariaAddressRepository;
import java.util.List;

@Service
public class SpringDataJpaMariaAddressService implements AddressService {
    private final SpringDataJpaMariaAddressRepository springDataJpaMariaAddressRepository;
    @Autowired
    public SpringDataJpaMariaAddressService(SpringDataJpaMariaAddressRepository springDataJpaMariaAddressRepository){
        this.springDataJpaMariaAddressRepository = springDataJpaMariaAddressRepository;
    }

    @Override
    public List<Address> listS() {
        pln("#SDJM - listS()");
        return springDataJpaMariaAddressRepository.findAll();
    }
    @Override
    public Address insertS(Address address) {
        Address addressDb = springDataJpaMariaAddressRepository.save(address);
        pln("#SDJM - insertS() addressDb: " + addressDb);
        return addressDb;
    }
    @Override
    public boolean deleteS(long seq) {
        pln("#SDJM - deleteS()");
        springDataJpaMariaAddressRepository.deleteById(seq);
        return true;
    }

    public List<Address> findByNameS(String name){
        pln("#SDJM - findByNameS()");
        return springDataJpaMariaAddressRepository.findByName(name);
    }
    public List<Address> findByNameAndAddrS(String name, String addr){
        pln("#SDJM - findByNameAndAddrS()");
        return springDataJpaMariaAddressRepository.findByNameAndAddr(name, addr);
    }
    public List<Address> findByNameOrAddrS(String name, String addr){
        pln("#SDJM - findByNameOrAddrS()");
        return springDataJpaMariaAddressRepository.findByNameOrAddr(name, addr);
    }
    public List<Address> findByNameContainingS(String name){
        pln("#SDJM - findByNameContainingS()");
        return springDataJpaMariaAddressRepository.findByNameContaining(name);
    }

    void pln(String str){
        System.out.println(str);
    }
}
