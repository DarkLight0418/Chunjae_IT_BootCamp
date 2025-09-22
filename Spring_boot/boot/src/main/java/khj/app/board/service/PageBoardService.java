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
    void deleteB(long seq);
}
