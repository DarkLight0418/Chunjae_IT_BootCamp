package khj.app.boot.repository;

import khj.app.boot.domain.Author;
import khj.app.boot.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataJpaMariaBookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(Author author);
}
