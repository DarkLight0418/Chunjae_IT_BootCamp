package khj.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
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
	//private FileService fileService;
	
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
	        /*  @RequestParam("seq") 
		 		- HTTP 요청 파라미터 중 "seq" 값을 메소드 인자로 바인딩
	            - 예: update.do?seq=10 이라면 long seq = 10 으로 매핑됨
	            - 기본적으로 required=true라서 "seq" 파라미터가 없으면 400 에러 발생
	            - 필요에 따라 (required=false, defaultValue="0") 옵션을 줄 수 있음
	    */
	    //  boardService.contentB(seq)
	    //  - 서비스 계층 호출
	    //  - 전달받은 seq(게시글 번호)로 DB에서 해당 게시글(Board 객체)을 조회
	    //  - DAO/Mapper에서 select 쿼리를 실행한 결과를 Board 객체로 반환
	    
	    Board board = boardService.contentB(seq);

	    // ModelAndView("board/update", "board", board)
	    //  - 뷰 이름: "board/update" → /WEB-INF/views/board/update.jsp 로 포워딩
	    //  - 모델 속성: 이름("board"), 값(board 객체)
	    //  - JSP에서 ${board.writer}, ${board.email} 형태로 데이터 접근 가능
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
	
	/*
	
	@GetMapping("form_mt.do")
	public String formMt() {
		return "file/form_mt";
	}
	@PostMapping("upload_mt.do")
	public String uploadMt(ArrayList<MultipartFile> files) {
		for(MultipartFile file :files) {
			String ofname = file.getOriginalFilename();
			if(ofname != null) ofname = ofname.trim();
			if(ofname.length() != 0) {
				String url = fileService.saveAtStore(file);
				//System.out.println("@Upload file URL: " + url);
			}
		}
		
		return "redirect:list.do";
	}

	
	@GetMapping("download.do")
	public ModelAndView downloadFile(@RequestParam("fname") String fname) {
		File file = new File("C:/Users/KIMHANJAE2/test", fname);
		return new ModelAndView(new FileDownloadView(), "downloadFile", file);
	}
	*/
}
