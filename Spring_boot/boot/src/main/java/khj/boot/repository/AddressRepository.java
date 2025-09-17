package khj.boot.repository;

import khj.boot.domain.Address;

import java.util.List;

public interface AddressRepository {
    List<Address> list();
    Address insert(Address address);
    boolean delete(long seq);
}
