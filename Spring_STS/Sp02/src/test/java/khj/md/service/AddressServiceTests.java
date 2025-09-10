package khj.md.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import khj.md.domain.Address;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AddressServiceTests {
	@Setter(onMethod_ =  {@Autowired})
	private AddressService addressSerivce; //Object Injection 
	
	/*
	@Test
	public void testListS() {
		pln("#addressSerivce: " + addressSerivce);
		List<Address> list = addressSerivce.listS();
		pln("#testListS() list: " + list);
	}
	@Test
	public void testInsertS() {
		Address address = new Address(-1L, "주소록", "서비스", null);
		addressSerivce.insertS(address);
		pln( "#testInsertS() 수행성공");
	}
	*/
	@Test
	public void testDeleteS() {
		long seq = 77L;
		addressSerivce.deleteS(seq);
		pln( "#testDeleteS() 수행성공");
	}
	
	void pln(String str) {
		System.out.println(str);
	}
}
