package khj.app.boot.service;

import khj.app.boot.domain.Author;
import java.util.List;

public interface AuthorService {
    Author insert(Author author); //C
    List<Author> getAuthors(); //R
    Author getAuthorWithBooks(Long authorId); //R
    Author update(Long authorId, String newName); //U
    void delete(Long authorId); //D
}