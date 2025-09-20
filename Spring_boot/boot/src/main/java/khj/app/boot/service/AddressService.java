package khj.app.boot.service;

import khj.app.boot.domain.Address;

import java.util.List;

public interface AddressService {
    List<Address> listS();
    Address insertS(Address address);
    boolean deleteS(long seq);
}
