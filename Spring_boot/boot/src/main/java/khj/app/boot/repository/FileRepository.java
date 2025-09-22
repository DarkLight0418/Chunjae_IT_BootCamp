package khj.app.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import khj.app.boot.domain.FileUp;

public interface FileRepository extends JpaRepository<FileUp, Long> {
}