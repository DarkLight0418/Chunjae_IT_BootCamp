package khj.app.boot.repository;

import khj.app.boot.domain.Address;

import java.util.List;

public interface AddressRepository2 {
    List<Address> list();
    boolean insert(Address address);
    boolean delete(long seq);
}
