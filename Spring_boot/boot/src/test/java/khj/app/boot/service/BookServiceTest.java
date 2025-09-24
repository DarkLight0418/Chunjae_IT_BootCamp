package khj.app.boot.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import khj.app.boot.domain.Author;
import khj.app.boot.domain.Book;
import java.util.List;

@SpringBootTest
class BookServiceTest { //미션
    @Autowired
    private BookService bookService;

    @Test
    void insertBook() {
        Book book = new Book();
        book.setTitle("누군가 널 위해 기도한당");

        Long authorId = 2L;
        Author author = new Author();
        author.setId(authorId);
        book.setAuthor(author);

        bookService.insertBook(book);
    }
    @Test
    void getBooks() {
        List<Book> books = bookService.getBooks();
        pln("getBooks() books: " + books);
    }
    @Test
    void testGetBooks() {
        Long authorId = 2L;
        Author author = new Author();
        author.setId(authorId);

        List<Book> books =bookService.getBooks(author);
        pln("testGetBooks() books: " + books);
    }
    @Transactional
    @Test
    void getBook() {
        Long bookId = 4L;
        Book book = bookService.getBook(bookId);
        String title = book.getTitle();
        Author author = book.getAuthor();
        pln("getBook() bookId: " + bookId + ", title: " + title + ", author: " + author);
    }
    @Test
    void updateBook() {
        Long bookId = 3L;
        String newTitle = "오라클UP";
        Book book = bookService.updateBook(bookId, newTitle);
        pln("updateBook() book: " + book);
    }
    @Test
    void deleteBook() {
        Long bookId = -1L;
        bookService.deleteBook(bookId);
    }

    void pln(String str){
        System.out.println(str);
    }
}