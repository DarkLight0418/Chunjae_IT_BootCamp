package khj.app.board.control;


import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import khj.app.board.domain.Board;
import khj.app.board.dto.BoardListResult;
import khj.app.board.service.BoardService;
import khj.app.board.service.PageBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("board_page")
@RequiredArgsConstructor
@Controller
public class PageBoardController {
    private final PageBoardService pageBoardService;
    private final BoardService boardService;

    @GetMapping("list.do")
    public String list(@PageableDefault(size = 3, sort = "seq", direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        Page<Board> list = pageBoardService.findAll(pageable);
        model.addAttribute("boardList", list);
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("hasNext", list.hasNext());
        return "board_page/list";
    }

    @GetMapping("write.do")
    public String write() {
        return "board_page/write";
    }
    @PostMapping("write.do")
    public String write(Board board) {
        pageBoardService.insertB(board);
        return "redirect:list.do";
    }

    @GetMapping("del.do")
    public String delete(@RequestParam("seq") long seq,
                         ServletContext application, HttpSession session, HttpServletRequest request, Object object) {
        application = session.getServletContext();

        pageBoardService.deleteB(seq);
        return "redirect:list.do";
    }

    @GetMapping("content.do")
    public ModelAndView content(@RequestParam("seq") long seq) {
        Board board = boardService.findBoardDetail(seq);
        return new ModelAndView("board_page/content", "board", board);
    }

    @GetMapping("update.do")
    public String goUpdate(@RequestParam("seq") long seq, Model model) {
        Board board = boardService.findBoardDetail(seq);
        model.addAttribute("board", board);
        return "board_page/update";
    }

    @PostMapping("update.do")
    public String update(Board board) {
        boardService.saveBoard(board);
        return "redirect:list.do";
    }
}
