package khj.board.mapper;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import khj.board.domain.Board;
import khj.board.domain.PageInfo;

public interface BoardMapper {
	List<Board> list(PageInfo pageInfo);
	void insert(Board board);
	void delete(long seq);
	Board content(long seq);
	int modify(Board board);
	int selectBoard(PageInfo pageInfo);
	int countBoard();
	void saveFile(long seq, MultipartFile file);
}
