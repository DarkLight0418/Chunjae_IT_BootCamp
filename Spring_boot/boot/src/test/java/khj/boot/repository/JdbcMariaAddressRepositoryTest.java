package khj.boot.repository;

import khj.app.boot.repository.JdbcMariaAddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import khj.app.boot.domain.Address;
import java.util.List;

@SpringBootTest
class JdbcMariaAddressRepositoryTest {
    @Autowired
    JdbcMariaAddressRepository jdbcMariaAddressRepository;

    @Test
    void getConnection() {
        pln("#jdbcMariaAddressRepository: " + jdbcMariaAddressRepository);
        pln("#con: " + jdbcMariaAddressRepository.getConnection());
    }
    @Test
    void list() {
        List<Address> list = jdbcMariaAddressRepository.list();
        pln("#list: " + list);
    }
    @Test
    void insert() {
        Address address = new Address(-1L, "서준서", "인천시", null);
        Address addressDb = jdbcMariaAddressRepository.insert(address);
        pln("#addressDb: " + addressDb);
    }
    @Test
    void delete() {
        boolean flag = jdbcMariaAddressRepository.delete(81);
        pln("#flag: " + flag);
    }
    void pln(String str){
        System.out.println(str);
    }
}