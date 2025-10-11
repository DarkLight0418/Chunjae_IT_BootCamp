package khj.app.repository;

import khj.app.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepo extends JpaRepository<Lecture, Long> {
}
