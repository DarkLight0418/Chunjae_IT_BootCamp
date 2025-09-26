package khj.app.board.service;

import khj.app.board.domain.Attachment;
import khj.app.board.domain.Board;
import khj.app.board.dto.BoardListResult;
import khj.app.boot.domain.Address;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PageBoardService {
    Page<Board> findAll(Pageable pageable);
    BoardListResult getBoardListResult(Pageable pageable);
    Board insertB(Board board, List<MultipartFile> files) throws IOException;
    Board findBoardDetail(long seq);
    void deleteB(long seq);
    public Page<Board> searchBySubject(String keyword, Pageable pageable);
    public Page<Board> searchByContent(String keyword, Pageable pageable);
    public Page<Board> searchByWriter(String keyword, Pageable pageable);

    ResponseEntity<Resource> getAttachment(Long id) throws IOException;
}
