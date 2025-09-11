package khj.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khj.board.domain.Board;
import khj.board.domain.PageInfo;
import khj.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<Board> listB(PageInfo pageInfo) {
		return boardMapper.list(pageInfo);
	}
	
	@Override
	public Board contentB(long seq) {
		return boardMapper.content(seq);
	}
	
	
	@Override
	public void insertB(Board board) {
		boardMapper.insert(board);
	}

	@Override
	public void deleteB(long seq) {
		boardMapper.delete(seq);
	}
	
	@Override
	public int modifyB(Board board) {
		return boardMapper.modify(board);
	}
	
	@Override
	public int countBoard() {
		return boardMapper.countBoard();
	}
	
	@Override
	public int selectBoard(PageInfo pageInfo) {
		return boardMapper.selectBoard(pageInfo);
	}
}
