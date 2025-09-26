package khj.app;

import jakarta.persistence.EntityManager;
import khj.app.board.mapper.BoardMapper;
import khj.app.board.repository.SpringDataJpaMariaBoardRepository;
// import khj.app.board.service.BoardService;
// import khj.app.board.service.JPAMyBatisBoardService;
import khj.app.board.service.PageBoardService;
import khj.app.board.service.SpringDataJpaPageBoardService;
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

 
//@Configuration // 💡 이 클래스가 스프링의 설정 클래스임을 명시
class SpringConfig_주석버전 {
/*
    // ============= 공통 Bean/DI 설명 =============
    // @Bean : 개발자가 직접 메서드를 작성해 스프링 컨테이너에 객체(Bean)를 등록하는 방법
    // Bean : 스프링 컨테이너(IoC 컨테이너)가 관리하는 객체(싱글톤으로 관리됨)
    // @Autowired : 스프링이 알아서 타입에 맞는 Bean을 찾아서 의존성 주입(Dependency Injection)해줌
    // ==========================================

    @Autowired 
    JdbcTemplate jdbcTemplate; 
    // 💡 DB와 직접 연결할 때 사용하는 Spring JDBC 헬퍼 객체 (여기서는 JPA와 비교용으로 존재)

    @Bean
    public AddressRepository2 addressRepository2(){
        //return new JdbcTemplateMariaAddressRepository(jdbcTemplate);
        // 💡 위 주석 코드처럼 JDBC 방식 Repository도 가능
        // 이번엔 JPA 기반의 Repository를 사용
        return new JpaMariaAddressRepository(entityManager);
    }

    @Bean
    public AddressService2 addressService2(){
        // 💡 Repository를 사용해 실제 로직을 수행하는 Service 레이어
        // JDBC, MyBatis, JPA 구현체 중 선택적으로 주입 가능
        //return new JdbcTemplateMariaAddressService(addressRepository2());
        //return new MybatisMariaAddressService(addressMapper);
        return new JpaMariaAddressService(addressRepository2());
    }

    @Autowired 
    AddressMapper addressMapper; 
    // 💡 MyBatis에서 SQL 매핑을 위한 Mapper 인터페이스 주입

    @Autowired 
    EntityManager entityManager; 
    // 💡 JPA가 DB와 상호작용할 때 사용하는 핵심 클래스 (영속성 컨텍스트 제공)


    @Autowired
    SpringDataJpaMariaAddressRepository springDataJpaMariaAddressRepository;
    // 💡 Spring Data JPA가 자동 구현해주는 Repository (인터페이스 기반)


    @Bean
    public AddressService addressService(){
        // 💡 Spring Data JPA Repository를 이용하는 Service 등록
        return new SpringDataJpaMariaAddressService(springDataJpaMariaAddressRepository);
    }

    @Autowired
    SpringDataJpaMariaBoardRepository boardRepository; 
    // 💡 게시판(Board)용 Spring Data JPA Repository

    @Autowired
    BoardMapper boardMapper; 
    // 💡 게시판(Board)용 MyBatis Mapper (SQL 직접 매핑용)


    @Bean
    public PageBoardService pageBoardService(SpringDataJpaMariaBoardRepository springDataJpaMariaBoardRepository){
        // 💡 게시판의 페이징 처리를 전담하는 Service 등록
        // 생성자에 Repository를 주입받아 사용
        return new SpringDataJpaPageBoardService(springDataJpaMariaBoardRepository);
    }
    */
}
