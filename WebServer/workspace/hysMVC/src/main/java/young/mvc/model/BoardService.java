package young.mvc.model;

import java.util.ArrayList;
import young.mvc.domain.Board;

public class BoardService {
    private BoardDAO dao;

    //SingleTon Object Model
    private static final BoardService instance = new BoardService();
    private BoardService() {
        dao = new BoardDAO();
    }
    public static BoardService getInstance() {
        return instance;
    }

    public ArrayList<Board> listS(){
        return dao.list();
    }
    public boolean insertS(Board dto) {
        return dao.insert(dto);
    }
    public Board showS(long seq){
        return dao.show(seq);
    }
    public boolean deleteS(long seq) {
        return dao.delete(seq);
    }
    public boolean updateS(long seq, String writer, String email, String subject, String content, String fname, String ofname) {
        return dao.update(seq, writer, email, subject, content, fname, ofname);
    }
    public String saveFileS() {
    	return null;
    }
}