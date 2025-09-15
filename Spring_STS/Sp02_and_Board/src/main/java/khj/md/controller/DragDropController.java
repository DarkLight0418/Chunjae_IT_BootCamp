package khj.md.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import khj.md.service.DragDropService;

@RequestMapping("file")
@Controller
public class DragDropController {
	@Autowired
	private DragDropService dragdropService;
	
	@GetMapping("file_dd.do")
	public String form() {
		return "drag_drop/form";
	}
	@PostMapping("fileUpload.do")
	public String fileUpload(MultipartHttpServletRequest mhsr) {
		dragdropService.setMultipartHttpServletRequest(mhsr);
		Map<String, List<Object>> map = dragdropService.getUploadFileName();
		pln("@DragdropController map: " + map);
		
		String appendData = mhsr.getParameter("temp");
		pln("@DragdropController appendData: " + appendData);
		
		return "redirect:list.do";
	}
	
	void pln(String str) {
		System.out.println(str);
	}
}
