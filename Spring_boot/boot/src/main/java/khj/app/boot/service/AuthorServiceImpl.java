package khj.app.boot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import khj.app.boot.domain.Author;
import khj.app.boot.repository.SpringDataJpaMariaAuthorRepository;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthorServiceImpl implements AuthorService {
    private final SpringDataJpaMariaAuthorRepository authorRepository;

    @Override
    public Author insert(Author author) {
        author = authorRepository.save(author);
        pln("#AuthorServiceImpl insert() author: " + author);
        return author;
    }
    @Override
    public List<Author> getAuthors() {
        List<Author> list = authorRepository.findAll();
        pln("#AuthorServiceImpl getAuthors() list: " + list);
        return list;
    }
    @Override
    public Author getAuthorWithBooks(Long authorId) {
        Author author = authorRepository.findByIdWithBooks(authorId);
        pln("#AuthorServiceImpl getAuthorWithBooks() author: " + author);
        return author;
    }

    @Override
    public Author update(Long authorId, String newName) {
        Author author = authorRepository.findByIdWithBooks(authorId);
        author.setName(newName);
        author = authorRepository.save(author);
        pln("#AuthorServiceImpl update() author: " + author);
        return author;
    }
    @Override
    public void delete(Long authorId) {
        authorRepository.deleteById(authorId);
        pln("#AuthorServiceImpl delete()");
    }

    void pln(String str){
        System.out.println(str);
    }
}
