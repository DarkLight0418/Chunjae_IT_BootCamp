package khj.app.boot.repository;

import khj.app.boot.domain.Address;

import java.util.List;

public interface AddressRepository {
    List<Address> list();
    Address insert(Address address);
    boolean delete(long seq);
}
