package khj.app.board.repository;

import khj.app.board.domain.Board;
import khj.app.board.domain.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class JdbcMariaBoardRepository implements BoardRepository {

    @Autowired
    private final DataSource dataSource;

    @Autowired
    public JdbcMariaBoardRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }


    @Override
    public List<Board> listR(PageInfo pageInfo) {
        return List.of();
    }


    @Override
    public Board insertR(Board board) {
        String sql = "insert into BOARD(writer, email, subject, content, date) values(?, ?, ?, ?, now())";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, board.getWriter());
            pstmt.setString(2, board.getEmail());
            pstmt.setString(3, board.getSubject());
            pstmt.setString(4, board.getContent());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                String seqStr = rs.getString(1);
                Board boardDb = getBoard(seqStr);
                return boardDb;
            } else {
                return null;
            }

        } catch (SQLException se) {
            return null;
        } finally {
            close(con, pstmt, rs);
        }
    }

    private Board getBoard(String seqStr) {
        String sql = "select * from Board where SEQ=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, seqStr);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                long seq = rs.getLong(1);
                String writer = rs.getString(2);
                String email = rs.getString(3);
                String subject = rs.getString(4);
                String content = rs.getString(5);
                Date date = rs.getDate(6);
                return new Board(seq, writer, email, subject, content, date);
            } else {
                return null;
            }
        } catch (SQLException se) {
            return null;
        } finally {
            close(con, pstmt, rs);
        }
    }

    @Override
    public boolean deleteR(long seq) {
        String sql = "delete from BOARD where SEQ=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, seq);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException se) {
            return false;
        }
    }

    @Override
    public Board contentR(long seq) {
        return null;
    }

    @Override
    public int modifyR(Board board) {
        return 0;
    }

    @Override
    public int countBoardR() {
        return 0;
    }

    @Override
    public int selectBoardR(PageInfo pageInfo) {
        return 0;
    }

    @Override
    public List<Board> findBySubjectR(String subject) {
        return List.of();
    }

    @Override
    public List<Board> findByContentR(String content) {
        return List.of();
    }

    @Override
    public List<Board> findByWriterR(String writer) {
        return List.of();
    }

    private void close(Connection con, Statement stmt, ResultSet rs) {
        try {
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        } catch (SQLException se) {}
    }
}
