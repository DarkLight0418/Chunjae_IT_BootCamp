package khj.boot.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import khj.boot.domain.Address;
import java.util.List;

@Transactional  // test에도 같이 필요한 어노테이션
@SpringBootTest
class JpaMariaAddressServiceTest {
    @Autowired
    AddressService2 addressService2;

    @Test
    void listS() {
        List<Address> list = addressService2.listS();
        pln("#JpaMariaAddressServiceTest listS() list: " + list);
    }
    @Commit  // commit 쓰지 않으면 자동으로 롤백됨
    @Test
    void insertS() {
        Address address = new Address(0L, "월", "요일", null);//seq:-1L 하면 안됨
        addressService2.insertS(address);
        pln("#JpaMariaAddressServiceTest insertS(): 성공");
    }
    @Commit
    @Test
    void deleteS() {
        long seq = 108L;
        addressService2.deleteS(seq);
        pln("#JpaMariaAddressServiceTest deleteS(): 성공");
    }

    void pln(String str){
        System.out.println(str);
    }
}