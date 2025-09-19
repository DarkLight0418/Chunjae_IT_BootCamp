package khj.board.service;

import khj.board.domain.Board;
import khj.board.domain.PageInfo;
import khj.board.repository.SpringDataJpaMariaBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringDataJPABoardService implements BoardService {

    @Autowired
    SpringDataJpaMariaBoardRepository springDataJpaMariaBoardRepository;

    @Override
    public List<Board> listB(PageInfo pageInfo) {
        return springDataJpaMariaBoardRepository.listB(pageInfo);
    }

    @Override
    public Board insertB(Board board) {
        return springDataJpaMariaBoardRepository.insertB(board);
    }

    @Override
    public void deleteB(long seq) {
        springDataJpaMariaBoardRepository.deleteB(seq);
    }

    @Override
    public Board contentB(long seq) {
        return springDataJpaMariaBoardRepository.contentB(seq);
    }

    @Override
    public int modifyB(Board board) {
        return springDataJpaMariaBoardRepository.modifyB(board);
    }

    @Override
    public int countBoard() {
        return springDataJpaMariaBoardRepository.countBoard();
    }

    @Override
    public int selectBoard(PageInfo pageInfo) {
        return springDataJpaMariaBoardRepository.selectBoard(pageInfo);
    }

    @Override
    public List<Board> findBySubject(String subject) {
        return springDataJpaMariaBoardRepository.findBySubject(subject);
    }

    @Override
    public List<Board> findByContent(String content) {
        return springDataJpaMariaBoardRepository.findByContent(content);
    }

    @Override
    public List<Board> findByWriter(String writer) {
        return springDataJpaMariaBoardRepository.findByWriter(writer);
    }
}
