package khj.app.boot.service;

import khj.app.boot.domain.Address;
import khj.app.boot.mapper.AddressMapper;

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
