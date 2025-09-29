package khj.app;

import jakarta.persistence.EntityManager;
import khj.app.board.mapper.BoardMapper;
import khj.app.board.repository.AttachmentRepository;
import khj.app.board.repository.PageBoardRepository;
// import khj.app.board.service.BoardService;
// import khj.app.board.service.JPAMyBatisBoardService;
import khj.app.board.service.PageBoardService;
import khj.app.board.service.PageBoardServiceImpl;
import khj.app.boot.service.AddressService;
import khj.app.boot.service.AddressService2;
import khj.app.boot.service.JpaMariaAddressService;
import khj.app.boot.service.SpringDataJpaMariaAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import khj.app.boot.mapper.AddressMapper;
import khj.app.boot.repository.AddressRepository2;
import khj.app.boot.repository.JpaMariaAddressRepository;
import khj.app.boot.repository.SpringDataJpaMariaAddressRepository;

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

    @Autowired
    PageBoardRepository boardRepository;

    @Autowired
    BoardMapper boardMapper;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Bean
    public PageBoardService pageBoardService(PageBoardRepository springDataJpaMariaBoardRepository){
        return new PageBoardServiceImpl(springDataJpaMariaBoardRepository, attachmentRepository);
    }
}
