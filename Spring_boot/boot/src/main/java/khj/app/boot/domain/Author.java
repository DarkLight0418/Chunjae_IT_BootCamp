package khj.app.boot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

@Entity  // 테이블로 바뀌는 것(테이블과 동일하게 해줄 필요 있음)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author { //Author:Book = 1:N
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    //@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //즉시로딩(비추)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch=FetchType.LAZY) //지연로딩: 서비스 구현클래스에 @Transactional 해 줘야 함
    @ToString.Exclude
    private List<Book> books = new ArrayList<>(); //getBooks()호출

    public void addBook(Book book){ // 연관 관계 메소드
        books.add(book);
        book.setAuthor(this);
    }
}
