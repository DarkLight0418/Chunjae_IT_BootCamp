package khj.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import khj.rest.domain.Reboard;
import java.util.List;

public interface ReboardRepository extends JpaRepository<Reboard, Long> {
    List<Reboard> findByName(String name); //추가
    List<Reboard> findBySubjectContaining(String subject); //추가
    List<Reboard> findByContentContaining(String content); //추가

    //List<Reboard>  findBySubjectOrContent(String subject, String content); //And, Or.. //생략
}