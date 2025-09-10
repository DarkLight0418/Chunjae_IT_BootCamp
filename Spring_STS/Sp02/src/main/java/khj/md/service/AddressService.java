package khj.md.service;

import java.util.List;

import khj.md.domain.Address;

public interface AddressService {  // public 있어야 함 - 다른 패키지에서 쓰려면!
	List<Address> listS(); // public abstract
	void insertS(Address adderss);
	void deleteS(long seq);
}
