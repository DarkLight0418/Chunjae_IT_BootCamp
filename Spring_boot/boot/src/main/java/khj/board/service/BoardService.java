package khj.board.service;

import khj.board.domain.Board;
import khj.board.domain.PageInfo;

import java.util.List;

public interface BoardService {
    List<Board> listB(PageInfo pageInfo);
    Board insertB(Board board);
    void deleteB(long seq);
    Board contentB(long seq);
    int modifyB(Board board);
    int countBoard();
    int selectBoard(PageInfo pageInfo);

    List<Board> findBySubject(String subject);
    List<Board> findByContent(String content);
    List<Board> findByWriter(String writer);
}
