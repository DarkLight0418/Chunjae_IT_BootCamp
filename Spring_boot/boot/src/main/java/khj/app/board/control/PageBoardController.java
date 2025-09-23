package khj.app.board.control;


import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import khj.app.board.domain.Board;
import khj.app.board.domain.PageInfo;
import khj.app.board.dto.BoardListResult;
import khj.app.board.repository.BoardRepository;
import khj.app.board.repository.SpringDataJpaMariaBoardRepository;
// import khj.app.board.service.BoardService;
import khj.app.board.service.PageBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    // private final BoardService boardService;
    private final SpringDataJpaMariaBoardRepository spBoardRepository;

    @GetMapping("list.do")
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("seq").descending());
        Page<Board> boardPage = spBoardRepository.findAll(pageable);

        // 블록 사이즈 5개로
        PageInfo pageInfo = new PageInfo(boardPage, 5);

        model.addAttribute("boardList", boardPage.getContent());  // 실제 데이터
        model.addAttribute("pageInfo", pageInfo);  // 페이징 정보
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
        Board board = pageBoardService.findBoardDetail(seq);
        return new ModelAndView("board_page/content", "board", board);
    }

    @GetMapping("update.do")
    public String goUpdate(@RequestParam("seq") long seq, Model model) {
        Board board = pageBoardService.findBoardDetail(seq);
        model.addAttribute("board", board);
        return "board_page/update";
    }

    @PostMapping("update.do")
    public String update(Board board) {
        pageBoardService.insertB(board);
        return "redirect:list.do";
    }

    @GetMapping("search.do")
    public String search(
            @RequestParam("type") String type,
            @RequestParam("keyword") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("seq").descending());
        Page<Board> boardPage;

        switch (type) {
            case "subject" :
                boardPage = pageBoardService.searchBySubject(keyword, pageable);
                break;
            case "content" :
                boardPage = pageBoardService.searchByContent(keyword, pageable);
                break;
            case "writer" :
                boardPage = pageBoardService.searchByWriter(keyword, pageable);
                break;
            default:
                boardPage = Page.empty(pageable);
        }

        // 페이지 계산 로직도 필요하니까
        PageInfo pageInfo = new PageInfo(boardPage, 5);

        model.addAttribute("boardList", boardPage.getContent());  // 실제 게시글 데이터들
        model.addAttribute("pageInfo", pageInfo);  // 페이징
        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);

        return "board_page/list";
    }
}
