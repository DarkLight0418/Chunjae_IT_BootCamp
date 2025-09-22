package khj.app.boot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import khj.app.boot.domain.Address;
import khj.app.boot.dto.AddressListResult;

public interface PageAddressService {
    Page<Address> findAll(Pageable pageable);
    AddressListResult getAddressListResult(Pageable pageable);
    Address insertS(Address address);
    void deleteS(long seq);
}

