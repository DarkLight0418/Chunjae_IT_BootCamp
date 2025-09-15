package khj.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import khj.md.domain.Address;
import khj.md.mapper.AddressAjaxMapper;

// 인터페이스는 안씀, 구현 클래스에 씀

@Service
public class AddressAjaxServiceImpl implements AddressAjaxService {
	@Autowired
	private AddressAjaxMapper addressAjaxMapper;
	
	@Override
	public List<Address> listS() {
		return addressAjaxMapper.list();
	}

	@Override
	public void insertS(Address address) {
		addressAjaxMapper.insert(address);

	}

	@Override
	public void deleteS(long seq) {
		addressAjaxMapper.delete(seq);
	}

	@Override
	public Address selectBySeqS(long seq) {
		return addressAjaxMapper.selectBySeq(seq);
	}

	@Override
	public List<Address> selectByNameS(String name) {

		return addressAjaxMapper.selectByName(name);
	}

}
