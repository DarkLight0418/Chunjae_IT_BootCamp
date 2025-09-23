package khj.app.boot.service;

import khj.app.boot.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;


    @Test
    void insertBook() {
        Book book = new Book();
    }

    @Test
    void getBooks() {
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