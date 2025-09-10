package khj.md.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import khj.md.domain.Address;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AddressMapperTests {
	@Autowired
	private AddressMapper addressMapper;
	
	/*
	@Test
	public void testList() {
		pln("#addressMapper: " + addressMapper);
		List<Address> list = addressMapper.list();
		pln("#testList() list: " + list);
	}
	@Test
	public void testInsert() {
		Address address = new Address(-1L, "마이", "바티스", null);
		addressMapper.insert(address);
		pln( "#testInsert() 수행성공");
	}
	*/
	@Test
	public void testDelete() {
		long seq = 78L;
		addressMapper.delete(seq);
		pln( "#testDelete() 수행성공");
	}
	
	void pln(String str) {
		System.out.println(str);
	}
}
