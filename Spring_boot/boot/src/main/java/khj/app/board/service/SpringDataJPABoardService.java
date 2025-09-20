package khj.app.board.service;

import khj.app.board.domain.Board;
import khj.app.board.repository.SpringDataJpaMariaBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringDataJPABoardService implements BoardService {

    private final SpringDataJpaMariaBoardRepository repo;

    @Autowired
    public SpringDataJPABoardService(SpringDataJpaMariaBoardRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Board> findBoardList(Pageable pageable) {
        return repo.findAll(pageable).getContent();
    }

    @Override
    public Board saveBoard(Board board) {
        return repo.save(board);
    }

    @Override
    public void removeBoardBySeq(long seq) {
        repo.deleteById(seq);
    }

    @Override
    public Board findBoardDetail(long seq) {  // optional 처리해서 Board로 꺼내야 함. (findById는 Optional<Board> 반환
        return repo.findById(seq).orElse(null);
    }
/*
    @Override
    public List<Board> updateBoard(Board board) {
        return springDataJpaMariaBoardRepository.updateBoard(board);
    }

 */
    @Override
    public int countAllBoards() {
        return (int) repo.count();
    }

    @Override
    public int selectBoardPage(Pageable pageable) {
        return repo.findAll(pageable).getTotalPages();
    }

    @Override
    public List<Board> findBySubject(String subject) {
        return repo.findBySubject(subject);
    }

    @Override
    public List<Board> findByContent(String content) {
        return repo.findByContent(content);
    }

    @Override
    public List<Board> findByWriter(String writer) {
        return repo.findByWriter(writer);
    }
}
