package khj.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import khj.boot.domain.Address;
import khj.boot.repository.AddressRepository2;
import java.util.List;

// @Service
public class JdbcTemplateMariaAddressService implements AddressService2 {
    private final AddressRepository2 addressRepository2;
    //@Autowired
    public JdbcTemplateMariaAddressService(AddressRepository2 addressRepository2){
        this.addressRepository2 = addressRepository2;
    }

    @Override
    public List<Address> listS() {
        return addressRepository2.list();
    }
    @Override
    public boolean insertS(Address address) {
        return addressRepository2.insert(address);
    }
    @Override
    public boolean deleteS(long seq) {
        return addressRepository2.delete(seq);
    }
}
