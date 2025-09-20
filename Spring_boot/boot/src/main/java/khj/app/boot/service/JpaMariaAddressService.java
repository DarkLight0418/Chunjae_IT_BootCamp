package khj.app.boot.service;

import jakarta.transaction.Transactional;
import khj.app.boot.domain.Address;
import khj.app.boot.repository.AddressRepository2;
import java.util.List;

//@Service
@Transactional // 추가 - JPA는 트랜잭션 안에서 사용해야 함
public class JpaMariaAddressService implements AddressService2 {
    private final AddressRepository2 addressRepository2;
    //@Autowired
    public JpaMariaAddressService(AddressRepository2 addressRepository2){
        this.addressRepository2 = addressRepository2;
    }

    @Override
    public List<Address> listS() {
        System.out.println("#JpaMariaAddressService list() 호출");
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
