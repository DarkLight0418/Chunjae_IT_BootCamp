package khj.app.boot.service;

import khj.app.boot.domain.Author;
import khj.app.boot.domain.Book;
import java.util.List;

public interface BookService {
    Book insertBook(Book book); //C
    List<Book> getBooks(); //R
    List<Book> getBooks(Author author); //R
    Book getBook(Long bookId); //R
    Book updateBook(Long bookId, String newTitle); //U
    void deleteBook(Long bookId); //D
}
