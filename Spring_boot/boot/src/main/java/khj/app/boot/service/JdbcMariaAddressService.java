package khj.app.boot.service;

import khj.app.boot.domain.Address;
import khj.app.boot.repository.AddressRepository;

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