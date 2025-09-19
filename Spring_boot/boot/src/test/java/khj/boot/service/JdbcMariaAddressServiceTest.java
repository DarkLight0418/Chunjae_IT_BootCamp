package khj.boot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

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