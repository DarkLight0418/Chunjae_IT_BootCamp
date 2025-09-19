package khj.board.control;


import khj.board.domain.Board;
import khj.board.domain.PageInfo;
import khj.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("board")
public class BoardController {
    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("list.do")
    public String list(Model model, PageInfo pageInfo) {
        List<Board> list = boardService.listB(pageInfo);
        model.addAttribute("list", list);
        return "board/list";
    }
}
