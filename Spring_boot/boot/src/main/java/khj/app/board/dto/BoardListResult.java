package khj.app.board.dto;

import khj.app.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardListResult {
    private Page<Board> list;
    private int page;
    private int size;
    private long totalCount;
    private long totalPageCount;
}
