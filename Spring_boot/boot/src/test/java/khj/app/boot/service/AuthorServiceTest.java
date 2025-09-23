package khj.app.boot.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import khj.app.boot.domain.Author;
import khj.app.boot.domain.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorServiceTest {
    @Autowired
    private AuthorService authorService;

    @Test
    void insert() {
        Author author = new Author();
        author.setName("강감찬");

        Book book1 = new Book();
        book1.setTitle("제목1");
        Book book2 = new Book();
        book2.setTitle("제목2");
        author.addBook(book1);
        author.addBook(book2);

        authorService.insert(author);
    }

    @Transactional
    @Test
    void getAuthors() {
        List<Author> authors = authorService.getAuthors();
        for(Author author: authors){
            List<Book> books = author.getBooks();
            pln("<작가이름:"+author.getName()+"(작가ID: "+author.getId()+")의 책들>");
            for(Book book: books){
                pln("-제목: " + book.getTitle() + ", 책ID: "+book.getId());
            }
        }
    }

    @Test
    void getAuthorWithBooks() {
        long authorId = 2L;
        Author author = authorService.getAuthorWithBooks(authorId);

        List<Book> books = author.getBooks();
        pln("<작가이름:"+author.getName()+"(작가ID: "+author.getId()+")의 책들>");
        for(Book book: books){
            pln("-제목: " + book.getTitle() + ", 책ID: "+book.getId());
        }
    }

    @Test
    void update() {
        long authorId = 5L;
        String newName = "서감찬";
        Author author = authorService.update(authorId, newName);
    }

    @Test
    void delete() {
        long authorId = 5L;
        authorService.delete(authorId);
    }

    void pln(String str){
        System.out.println(str);
    }
}