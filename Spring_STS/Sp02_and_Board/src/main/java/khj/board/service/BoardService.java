package khj.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import khj.board.domain.Board;
import khj.board.domain.PageInfo;

public interface BoardService {
	List<Board> listB(PageInfo pageInfo);
	void insertB(Board board);
	void deleteB(long seq);
	Board contentB(long seq);
	int modifyB(Board board);
	int countBoard();
	int selectBoard(PageInfo pageInfo);
	//void saveFile(long seq, MultipartFile file);
	
	List<Board> searchBySubject(String subject);
	List<Board> searchByContent(String content);
	List<Board> searchByWriter(String writer);
}
