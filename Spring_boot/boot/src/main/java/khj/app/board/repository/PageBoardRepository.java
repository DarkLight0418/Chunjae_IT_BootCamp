package khj.app.board.repository;

import khj.app.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageBoardRepository extends JpaRepository<Board, Long> {
    /*

    List<Board> findBoardList(Pageable pageable);
    Board saveBoard(Board board);
    void removeBoardBySeq(long seq);
    Board findBoardDetail(long seq);
    //List<Board> updateBoard(Board board);
    int countAllBoards();
    int selectBoardPage(Pageable pageable);
*/

    Page<Board> findBySubjectContaining(String keyword, Pageable pageable);//테이블 컬럼에 의존적인 select는 직접 만들어줘야 함
    //JPQL -> select a from Address a where a.name=:name
    Page<Board> findByContentContaining(String keyword, Pageable pageable); //And 연산자
    Page<Board> findByWriterContaining(String keyword,Pageable pageable); //Or 연산자
    //List<Board> findByNameContaining(String name); //XXXContaining()은 like연산자

    Page<Board> findByOrderBySeqDesc(Pageable pageable);
}