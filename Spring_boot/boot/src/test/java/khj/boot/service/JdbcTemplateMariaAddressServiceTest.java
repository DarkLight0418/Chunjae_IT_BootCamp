package khj.boot.service;

import khj.app.boot.service.AddressService2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import khj.app.boot.domain.Address;

import java.util.List;

@SpringBootTest
class JdbcTemplateMariaAddressServiceTest {
    @Autowired
    AddressService2 addressService2;

    @Test
    void listS() {
        List<Address> list = addressService2.listS();
        pln("##Template list: " + list);
    }
    @Test
    void insertS() {
        Address address = new Address(-1L, "오늘은", "목요일", null);
        boolean flag = addressService2.insertS(address);
        pln("##Template insert flag: " + flag);
    }
    @Test
    void deleteS() {
        long seq = 99L;
        boolean flag = addressService2.deleteS(seq);
        pln("##Template delete flag: " + flag);
    }

    private void pln(String str){
        System.out.println(str);
    }
}