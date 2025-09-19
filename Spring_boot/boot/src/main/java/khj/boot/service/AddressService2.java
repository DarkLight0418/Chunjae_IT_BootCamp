package khj.boot.service;

import khj.boot.domain.Address;

import java.util.List;

public interface AddressService2 {
    List<Address> listS();
    boolean insertS(Address address);
    boolean deleteS(long seq);
}
