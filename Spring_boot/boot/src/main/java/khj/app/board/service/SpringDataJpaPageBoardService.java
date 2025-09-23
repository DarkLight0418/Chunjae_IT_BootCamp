package khj.app.board.service;

import jakarta.transaction.Transactional;
import khj.app.board.domain.Board;
import khj.app.board.dto.BoardListResult;
import khj.app.board.repository.SpringDataJpaMariaBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class SpringDataJpaPageBoardService implements PageBoardService {
    @Autowired
    private final SpringDataJpaMariaBoardRepository springDataJpaMariaBoardRepository;

    @Override
    public Page<Board> findAll(Pageable pageable) {
        Page<Board> pList = springDataJpaMariaBoardRepository.findByOrderBySeqDesc(pageable);

        return pList;
    }

    @Override
    public Board findBoardDetail(long seq) {
        return springDataJpaMariaBoardRepository.findById(seq).orElse(null);
    }

    @Override
    public BoardListResult getBoardListResult(Pageable pageable) {
        Page<Board> pList = springDataJpaMariaBoardRepository.findByOrderBySeqDesc(pageable);
        int page = pList.getNumber(); //페이지번호
        int size = pList.getSize(); //페이지크기
        long totalCount = pList.getTotalElements(); //글의 총갯수
        int totalPageCount = pList.getTotalPages(); //페이지 총갯수

        System.out.println("#Service page: "+page+", size: "+ size
                + ", totalCount: "+totalCount +", totalPageCount :"+totalPageCount);

        return new BoardListResult(pList, page, size, totalCount, totalPageCount);
    }

    @Override
    public Board insertB(Board board) {
        board = springDataJpaMariaBoardRepository.save(board);
        return board;
    }

    @Override
    public void deleteB(long seq) {
        springDataJpaMariaBoardRepository.deleteById(seq);
    }


    @Override
    public Page<Board> searchBySubject(String keyword, Pageable pageable) {
        return springDataJpaMariaBoardRepository.findBySubjectContaining(keyword, pageable);
    }

    @Override
    public Page<Board> searchByContent(String keyword, Pageable pageable) {
        return springDataJpaMariaBoardRepository.findByContentContaining(keyword, pageable);
    }

    @Override
    public Page<Board> searchByWriter(String keyword, Pageable pageable) {
        return springDataJpaMariaBoardRepository.findByWriterContaining(keyword, pageable);
    }
}
