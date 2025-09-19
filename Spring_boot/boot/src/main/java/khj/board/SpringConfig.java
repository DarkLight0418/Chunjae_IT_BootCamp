package khj.board;

import jakarta.persistence.EntityManager;
import khj.boot.mapper.AddressMapper;
import khj.boot.repository.AddressRepository2;
import khj.boot.repository.JpaMariaAddressRepository;
import khj.boot.repository.SpringDataJpaMariaAddressRepository;
import khj.boot.service.AddressService;
import khj.boot.service.AddressService2;
import khj.boot.service.JpaMariaAddressService;
import khj.boot.service.SpringDataJpaMariaAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class SpringConfig {
    @Autowired JdbcTemplate jdbcTemplate;
    @Bean
    public AddressRepository2 addressRepository2(){
        //return new JdbcTemplateMariaAddressRepository(jdbcTemplate);
        return new JpaMariaAddressRepository(entityManager);
    }
    @Bean
    public AddressService2 addressService2(){
        //return new JdbcTemplateMariaAddressService(addressRepository2());
        //return new MybatisMariaAddressService(addressMapper);
        return new JpaMariaAddressService(addressRepository2());
    }
    @Autowired AddressMapper addressMapper;
    @Autowired EntityManager entityManager;


    @Autowired
    SpringDataJpaMariaAddressRepository springDataJpaMariaAddressRepository;
    @Bean
    public AddressService addressService(){
        return new SpringDataJpaMariaAddressService(springDataJpaMariaAddressRepository);
    }
}
