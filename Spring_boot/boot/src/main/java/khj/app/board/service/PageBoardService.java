package khj.app.board.service;

import khj.app.board.domain.Board;
import khj.app.board.dto.BoardListResult;
import khj.app.boot.domain.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageBoardService {
    Page<Board> findAll(Pageable pageable);
    BoardListResult getBoardListResult(Pageable pageable);
    Board insertB(Board board);
    Board findBoardDetail(long seq);
    void deleteB(long seq);
    public Page<Board> searchBySubject(String keyword, Pageable pageable);
    public Page<Board> searchByContent(String keyword, Pageable pageable);
    public Page<Board> searchByWriter(String keyword, Pageable pageable);
}
