package khj.boot.repository;

import khj.app.boot.repository.AddressRepository2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import khj.app.boot.domain.Address;

import java.util.List;

@SpringBootTest
class JdbcTemplateMariaAddressRepositoryTest {
    @Autowired
    AddressRepository2 addressRepository2;

    @Test
    void list() {
        List<Address> list =  addressRepository2.list();
        pln("#Template list: " + list);
    }
    @Test
    void insert() {
        Address address = new Address(-1L, "템플릿",  "테스트", null);
        boolean flag = addressRepository2.insert(address);
        pln("#Template insert flag: " + flag);
    }
    @Test
    void delete() {
        boolean flag = addressRepository2.delete(98);
        pln("#Template delete flag: " + flag);

    }

    private void pln(String str){
        System.out.println(str);
    }
}