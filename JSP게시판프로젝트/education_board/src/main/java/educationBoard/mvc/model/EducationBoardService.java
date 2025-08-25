package educationBoard.mvc.model;

import java.util.ArrayList;

import educationBoard.mvc.domain.EducationBoard;


public class EducationBoardService {
	private EducationBoardDAO dao;
	private static final EducationBoardService instance = new EducationBoardService();
	
	private EducationBoardService() {
		dao = new EducationBoardDAO();
	}
	
	public ArrayList<EducationBoard> list() {
		return dao.list();
	}
	public void insert(EducationBoard dto) {
		dao.insert(dto);
	}
	public void delete(long seq) {
		dao.delete(seq);
	}
	public EducationBoard content(EducationBoard dto) {
		return dao.content(dto);
	}
	public void update(EducationBoard dto) {
		dao.update(dto);
	}
	
	public static EducationBoardService getInstance() {
		return instance;
	}
}
