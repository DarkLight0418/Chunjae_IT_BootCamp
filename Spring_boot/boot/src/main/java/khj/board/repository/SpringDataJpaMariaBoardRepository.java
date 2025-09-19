package khj.board.repository;

import khj.board.domain.Board;
import khj.board.domain.PageInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataJpaMariaBoardRepository extends JpaRepository<Board, Long> {
    List<Board> listB(PageInfo pageInfo);
    Board insertB(Board board);
    void deleteB(long seq);
    Board contentB(long seq);
    int modifyB(Board board);
    int countBoard();
    int selectBoard(PageInfo pageInfo);

    List<Board> findBySubject(String subject);//테이블 컬럼에 의존적인 select는 직접 만들어줘야 함
    //JPQL -> select a from Address a where a.name=:name
    List<Board> findByContent(String content); //And 연산자
    List<Board> findByWriter(String writer); //Or 연산자
    //List<Board> findByNameContaining(String name); //XXXContaining()은 like연산자
}