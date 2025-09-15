package khj.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import khj.board.domain.Board;
import khj.board.domain.PageInfo;
import khj.board.service.BoardService;
import khj.board.service.FileService;
import khj.md.fileset.FileDownloadView;
import khj.md.fileset.path.Path;
import lombok.AllArgsConstructor;

@AllArgsConstructor  // 생성자
@Controller  // 컨트롤러 인식
@RequestMapping("board")
public class BoardController {
	
	private BoardService boardService;

	
	/*
	@GetMapping("list.do")
	public ModelAndView list() {
		return new ModelAndView("board/list","boardList", boardService.listB());
	}
	// 임시로 비교하라고 남겨둔 거(지금 작동하는 건 list 메소드에서 페이징도 같이 합니다)
	*/
	
	@GetMapping("list.do")  // cp : current page, ps : page size (@RequestParam 자동으로 매핑)
	public ModelAndView list(@RequestParam(defaultValue = "1") int cp,
			@RequestParam(defaultValue = "5") int ps, @RequestParam(defaultValue = "5") int pageTotalNum) {
		int total = boardService.countBoard();
	
		PageInfo pageInfo = new PageInfo(total, cp, ps, pageTotalNum);
		List<Board> list = boardService.listB(pageInfo);

		System.out.println("[DEBUG] pageNum=" + pageInfo.getPageNum());
		System.out.println("[DEBUG] startRow=" + pageInfo.getStartRow());
		System.out.println("[DEBUG] rowCount=" + pageInfo.getRowCount());
		
		ModelAndView mv = new ModelAndView("board/list");
		mv.addObject("boardList", list);
		mv.addObject("pageInfo", pageInfo);
		return mv;
	}
	
	@GetMapping("content.do")
	public ModelAndView viewContent(@RequestParam("seq") long seq) {
		Board board = boardService.contentB(seq);
		return new ModelAndView("board/content", "board", board);
	}
	
	// 글쓰긔 & 파일 첨부
	@GetMapping("write.do")
	public String viewWrite() {
		return "board/write";
	}
	
	@PostMapping("write.do")
	//public String insert(Board board) {
	public String insert(Board board, @RequestParam("file") MultipartFile file) throws IOException {
		boardService.insertB(board);
		
		/*
		if(!file.isEmpty()) {
			boardService.saveFile(board.getSeq(), file);
		}
		
		*/
		return "redirect:list.do";
	}
	
	// 수정
	
	// update.do 요청이 들어왔을 때 실행되는 컨트롤러 메소드
	@GetMapping("update.do")  
	public ModelAndView viewUpdate(@RequestParam("seq") long seq) {
	    Board board = boardService.contentB(seq);
	    return new ModelAndView("board/update", "board", board);
	}

	@GetMapping("del.do")
	public String delete(long seq) {
		boardService.deleteB(seq);
		
		return "redirect:list.do";
	}
	
	
	@PostMapping("update.do")
	public String modify(Board board) {
		boardService.modifyB(board);
		
		return "redirect:list.do";
	}
	
	@GetMapping("search.do")
	public String search(
			@RequestParam("type") String type,
			@RequestParam("keyword") String keyword,
			Model model
			) {
		
		List<Board> boardList;
		
		switch (type) {
			case "subject" :
				boardList = boardService.searchBySubject(keyword);
				System.out.println("제목 입력된 값 : " + keyword);
				break;
			case "content" :
				boardList = boardService.searchByContent(keyword);
				System.out.println("내용 입력된 값 : " + keyword);
				break;
			case "writer" :
				boardList = boardService.searchByWriter(keyword);
				System.out.println("작성자 입력된 값 : " + keyword);
				break;
			default:
				boardList = Collections.emptyList();
		}
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("type", type);
		
		/*
		 value(list) 객체를 name("list") 이름으로 추가함.
		 뷰 코드에서는 name("list")으로 지정한 이름을 통해서 value(list)를 사용함.
		*/
		
		return "board/list";
	}
}
