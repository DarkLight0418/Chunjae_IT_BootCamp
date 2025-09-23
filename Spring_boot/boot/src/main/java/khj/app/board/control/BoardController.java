package khj.app.board.control;

/*

import khj.app.board.domain.Board;
import khj.app.board.domain.PageInfo;
import khj.app.board.service.BoardService;
//import khj.app.board.service.JPAMyBatisBoardService;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Autowired
    private BoardService boardService;

    @Autowired
    private JPAMyBatisBoardService JPAMyBatisBoardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("list.do")
    public String list(Model model, @RequestParam(value="cp", defaultValue="1") int cp,
                       @RequestParam(value="ps", defaultValue="10") int ps) {
        int totalCount = boardService.countBoard();
        PageInfo pageInfo = new PageInfo(totalCount, cp, ps, 5);
        List<Board> boardList = boardService.findBoardList(pageInfo);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageInfo", pageInfo);

        return "board/list";
    }

    @GetMapping("write.do")
    public String goWrite() {
        return "board/write";
    }

    @PostMapping("write.do")
    public String write(Board board, @RequestParam("file") MultipartFile file) throws IOException {
        boardService.saveBoard(board);

        return "redirect:list.do";
    }

    @GetMapping("content.do")
    public ModelAndView content(@RequestParam("seq") long seq) {
        Board board = boardService.findBoardDetail(seq);
        return new ModelAndView("board/content", "board", board);
    }

    @GetMapping("del.do")
    public String delete(long seq) {
        boardService.removeBoardBySeq(seq);
        return "redirect:list.do";
    }

    @GetMapping("update.do")
    public String goUpdate(@RequestParam("seq") long seq, Model model) {
        Board board = boardService.findBoardDetail(seq);
        model.addAttribute("board", board);
        return "board/update";
    }

    @PostMapping("update.do")
    public String update(Board board) {
        boardService.saveBoard(board);
        return "redirect:list.do";
    }
 */
