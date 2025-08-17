package board.mvc.model;

import java.util.ArrayList;

import board.mvc.domain.Board;

public class BoardService {
	private BoardDAO dao;
	private static final BoardService instance = new BoardService();
	
	private BoardService() {
		dao = new BoardDAO();
	}
	public ArrayList<Board> list() {
		return dao.list();
	}
	public void insert(Board dto) {
		dao.insert(dto);
	}
	public void delete(long seq) {
		dao.delete(seq);
	}
	public Board Content(Board dto) {
		return dao.content(dto);
	}
	public void update(Board dto) {
		dao.update(dto);
	}
	
	public static BoardService getInstance() {
		return instance;
	}
}
