package khj.boot.service;

import khj.app.boot.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class JdbcMariaAddressServiceTest {

    @Autowired
    AddressService addressService;

    @Test
    void listS() {
        addressService.listS();
    }

    @Test
    void insertS() {
    }

    @Test
    void deleteS() {
    }
}