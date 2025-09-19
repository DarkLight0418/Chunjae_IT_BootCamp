package khj.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import khj.boot.domain.Address;
import java.util.List;

public interface SpringDataJpaMariaAddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByName(String name);//테이블 컬럼에 의존적인 select는 직접 만들어줘야 함
    //JPQL -> select a from Address a where a.name=:name
    List<Address> findByNameAndAddr(String name, String addr); //And 연산자
    List<Address> findByNameOrAddr(String name, String addr); //Or 연산자
    List<Address> findByNameContaining(String name); //XXXContaining()은 like연산자
}