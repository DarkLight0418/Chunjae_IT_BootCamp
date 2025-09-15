package khj.md.mapper;

import java.util.*;

import khj.md.domain.Address;

public interface AddressAjaxMapper {
	List<Address> list();
	void insert(Address address);
	void delete(long seq);
	
	Address selectBySeq(long seq);
	List<Address> selectByName(String name);
}
