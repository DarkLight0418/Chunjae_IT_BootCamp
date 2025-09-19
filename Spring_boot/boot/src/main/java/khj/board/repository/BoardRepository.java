package khj.board.repository;

import khj.board.domain.Board;
import khj.board.domain.PageInfo;

import java.util.List;

public interface BoardRepository {
    List<Board> listR(PageInfo pageInfo);
    Board insertR(Board board);
    boolean deleteR(long seq);
    Board contentR(long seq);
    int modifyR(Board board);
    int countBoardR();
    int selectBoardR(PageInfo pageInfo);

    List<Board> findBySubjectR(String subject);
    List<Board> findByContentR(String content);
    List<Board> findByWriterR(String writer);
}
