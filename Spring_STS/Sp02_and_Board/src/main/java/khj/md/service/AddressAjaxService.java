package khj.md.service;

import java.util.List;

import khj.md.domain.Address;

public interface AddressAjaxService {
	List<Address> listS();
	void insertS(Address address);
	void deleteS(long seq);
	
	Address selectBySeqS(long seq);
	List<Address> selectByNameS(String name);
}
