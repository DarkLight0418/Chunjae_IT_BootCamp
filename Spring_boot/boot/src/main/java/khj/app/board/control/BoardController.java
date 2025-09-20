package khj.app.board.control;


import khj.app.board.domain.Board;
import khj.app.board.domain.PageInfo;
import khj.app.board.service.BoardService;
import khj.app.board.service.SpringDataJPABoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("board")
public class BoardController {
    private BoardService boardService;
    private SpringDataJPABoardService springDataJPABoardService;

    @Autowired
    public BoardController(SpringDataJPABoardService springDataJPABoardService) {
        this.springDataJPABoardService = springDataJPABoardService;
    }

    @GetMapping("list.do")
    public String list(Model model, Pageable pageable) {
        List<Board> list = springDataJPABoardService.findBoardList(pageable);
        model.addAttribute("boardList", list);
        return "board/list";
    }

    @GetMapping("write.do")
    public String goWrite() {
        return "board/write";
    }

    @PostMapping("write.do")
    public String write(Board board, @RequestParam("file") MultipartFile file) throws IOException {
        springDataJPABoardService.saveBoard(board);

        return "redirect:list.do";
    }

    @GetMapping("content.do")
    public ModelAndView content(@RequestParam("seq") long seq) {
        Board board = springDataJPABoardService.findBoardDetail(seq);
        return new ModelAndView("board/content", "board", board);
    }

    @GetMapping("del.do")
    public String delete(long seq) {
        springDataJPABoardService.removeBoardBySeq(seq);
        return "redirect:list.do";
    }

    @GetMapping("update.do")
    public String goUpdate() {
        return "board/update";
    }

    @PostMapping("update.do")
    public String update(Board board) {
        springDataJPABoardService.saveBoard(board);
        return "redirect:list.do";
    }
}
