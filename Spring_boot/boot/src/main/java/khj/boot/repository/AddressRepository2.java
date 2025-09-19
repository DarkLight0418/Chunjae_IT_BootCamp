package khj.boot.repository;

import khj.boot.domain.Address;

import java.util.List;

public interface AddressRepository2 {
    List<Address> list();
    boolean insert(Address address);
    boolean delete(long seq);
}
