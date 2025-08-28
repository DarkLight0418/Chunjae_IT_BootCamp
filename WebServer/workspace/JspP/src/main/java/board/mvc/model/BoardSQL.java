package board.mvc.model;

public class BoardSQL {
    public final static String LIST = "SELECT SEQ, WRITER, EMAIL, SUBJECT, CONTENT, RDATE FROM BOARD ORDER BY SEQ DESC";
    public final static String INSERT = "INSERT INTO board (writer, email, subject, content) VALUES (?, ?, ?, ?)";
    public final static String DELETE = "DELETE FROM board WHERE seq = ?";
    public final static String UPDATECONTENT = "SELECT * FROM board WHERE seq=?";
    public final static String DOUPDATE = "UPDATE board SET email=?, subject=?, content=? WHERE seq=?";
}