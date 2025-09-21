package khj.app.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import khj.app.board.domain.Board;
import khj.app.board.domain.PageInfo;

@Mapper
public interface BoardMapper {
	List<Board> listB(PageInfo pageInfo);
	// void insert(Board board);
	// void delete(long seq);
	// Board content(long seq);
	// int modify(Board board);
	int selectBoard(PageInfo pageInfo);
	int countBoard();
	// void saveFile(long seq, MultipartFile file);
	// List<Board> searchBySubject(@Param("subject") String subject);
	// List<Board> searchByContent(@Param("content") String content);
	// List<Board> searchByWriter(@Param("writer") String writer);
}
