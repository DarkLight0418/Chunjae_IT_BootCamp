package khj.boot.service;

import khj.boot.domain.Address;
import khj.boot.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//@Service
public class JdbcMariaAddressService implements AddressService {
    private final AddressRepository addressRepository;
    //@Autowired
    public JdbcMariaAddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> listS() {
        return addressRepository.list();
    }
    @Override
    public Address insertS(Address address) {
        return addressRepository.insert(address);
    }
    @Override
    public boolean deleteS(long seq) {
        return addressRepository.delete(seq);
    }
}