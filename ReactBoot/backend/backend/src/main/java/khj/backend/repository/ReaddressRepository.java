package khj.backend.repository;

import khj.backend.domain.Readdress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//@RepositoryRestResource(path="places")   // 경로 : /api/places -> places로 바뀜!!!
@RepositoryRestResource
public interface ReaddressRepository extends JpaRepository<Readdress, Long> {
    List<Readdress> findByZip(@Param("zip") String zip); //추가
    List<Readdress> findByAddrContaining(@Param("addr") String addr); //추가
}
