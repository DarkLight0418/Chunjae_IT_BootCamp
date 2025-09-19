package khj.boot.service;

import khj.boot.domain.Address;
import khj.boot.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class MybatisMariaAddressService implements AddressService2 {
    //@Autowired
    private final AddressMapper addressMapper;

    public MybatisMariaAddressService(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public List<Address> listS() {
        return addressMapper.list();
    }

    @Override
    public boolean insertS(Address address) {
        return addressMapper.insert(address);
    }

    @Override
    public boolean deleteS(long seq) {
        return addressMapper.delete(seq);
    }
}
