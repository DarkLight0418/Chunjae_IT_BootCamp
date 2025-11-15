package khj.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import khj.backend.domain.Reuser;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

// @RepositoryRestResource(exported = false)  // 서비스 정지
public interface ReuserRepository extends JpaRepository<Reuser, Long> {
    Optional<Reuser> findByUsername(String username);
    List<Reuser> findByName(String name);
}