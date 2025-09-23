package khj.app.boot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import khj.app.boot.domain.Author;
import khj.app.boot.domain.Book;
import khj.app.boot.repository.SpringDataJpaMariaBookRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class BookServiceImpl implements BookService {
    private final SpringDataJpaMariaBookRepository bookRepository;

    @Override
    public Book insertBook(Book book) {
        book = bookRepository.save(book);
        pln("#BookServiceImpl insertBook() book: " + book);
        return book;
    }
    @Override
    public List<Book> getBooks() {
        List<Book> list = bookRepository.findAll();
        pln("#BookServiceImpl getBooks() list: " + list);
        return list;
    }
    @Override
    public List<Book> getBooks(Author author) {
        List<Book> list = bookRepository.findByAuthor(author);
        pln("#BookServiceImpl getBooks() 작가ID: "+author.getId()+"=> list: " + list);
        return list;
    }
    @Override
    public Book getBook(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book book = optionalBook.orElse(null);
        pln("#BookServiceImpl getBook() book: " + book);
        return book;
    }
    @Override
    public Book updateBook(Long bookId, String newTitle) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            book.setTitle(newTitle);
            book = bookRepository.save(book);
            pln("#BookServiceImpl updateBook() book: " + book);
            return book;
        }else {
            return null;
        }
    }
    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
        pln("#BookServiceImpl deleteBook()");
    }

    void pln(String str){
        System.out.println(str);
    }
}
