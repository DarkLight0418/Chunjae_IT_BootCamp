package khj.app.boot.service;

import khj.app.boot.domain.Author;
import khj.app.boot.domain.Book;
import khj.app.boot.repository.SpringDataJpaMariaAuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private SpringDataJpaMariaAuthorRepository authorRepo;


    @Test
    void insertBook() {
        Author author = new Author();
        author.setName("갓창우");
        authorRepo.save(author);

        Book book = new Book();
        book.setTitle("프로그래밍 천재의 삶");
        book.setAuthor(author);
        bookService.insertBook(book);
        System.out.println("insertBook() 메소드 잘 들어갔니~?" + book);
    }

    @Test
    void getBooks() {
        List<Book> books = bookService.getBooks();
        for (Book book : books) {
            System.out.println("book: " + book);
        }
    }

    @Test
    void testGetBooks() {
    }

    @Test
    void getBook() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }
}