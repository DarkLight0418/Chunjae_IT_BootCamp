package khj.app.boot.service;

import khj.app.boot.domain.Address;
import java.util.List;

public interface AddressAjaxService {
    List<Address> listS();
    Address insertS(Address address);
    void deleteS(long seq);

    //for Ajax
    Address getBySeqS(long seq);
    List<Address> getListByNameS(String name);
}
