package khj.app.board.service;

/*
@Service
public class JPAMyBatisBoardService implements BoardService {

    private final SpringDataJpaMariaBoardRepository repo;  // JPA
    private final BoardMapper boardMapper;  // MyBatis


    @Autowired
    public JPAMyBatisBoardService(SpringDataJpaMariaBoardRepository repo,
                                  BoardMapper boardMapper) {
        this.repo = repo;
        this.boardMapper = boardMapper;
    }

    @Override
    public List<Board> findBoardList(PageInfo pageInfo) {
        return boardMapper.listB(pageInfo);
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

    // Mybatis 전용
    @Override
    public int selectBoard(PageInfo pageInfo) {
        return boardMapper.selectBoard(pageInfo);
    }
    @Override
    public int countBoard() {
        return boardMapper.countBoard();
    }
}
*/