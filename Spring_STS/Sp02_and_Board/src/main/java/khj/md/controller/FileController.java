package khj.md.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import khj.md.service.FileUploadService;

@Controller
@RequestMapping("file")
public class FileController {
	@Autowired
	private FileUploadService fileUploadService;
	
	@GetMapping("form.do")
	public String form() {
		return "file/form";
	}
	
	@PostMapping("upload.do")
	public String upload(String name, MultipartFile file) {
		//pln("@FileController name: " + name);
		//pln("@FileController file: " + file);
		
		String ofname = file.getOriginalFilename();
		if(ofname != null) ofname = ofname.trim();
		if(ofname.length() != 0) {
			String url = fileUploadService.saveAtStore(file);
			pln("@Upload file URL: " + url);
		}
		
		return "";
		//return "redirect:list.do";
	}
	
	void pln(String str) {
		System.out.println(str);
	}
}
