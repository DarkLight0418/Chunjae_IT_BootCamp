package khj.md.controller;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import khj.md.fileset.path.Path;
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
		
		//return "flie/list";
		return "redirect:list.do";
	}
	
	@GetMapping("list.do")
	public ModelAndView fileList() {
		File fStore = new File(Path.FILE_STORE);
		if (fStore.exists()) fStore.mkdirs();  // 하나의 뎁스 mkdir(), 두 개 이상 mkdirs();
		
		File files[] = fStore.listFiles();
	
		return new ModelAndView("file/list", "files", files);
		// 모델 앤드 뷰의 모델 -> 변수와 비슷
	}
	
	/*
	@GetMapping("list.do")
	public ModelAndView fileList() {
		File fStore = new File(Path.FILE_STORE);
		if(!fStore.exists()) fStore.mkdirs();
		File files[] = fStore.listFiles();
		
		return new ModelAndView("file/list", "files", files);
	}
	*/
	@GetMapping("del.do")
	public String del(String fname) {
		File file = new File(Path.FILE_STORE + fname);
		if(file.exists()) file.delete();
		
		return "redirect:list.do"; 
	}
	
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
				String url = fileUploadService.saveAtStore(file);
				//pln("@Upload file URL: " + url);
			}
		}
		
		return "redirect:list.do";
	}
	
	@GetMapping("download.do")
	public ModelAndView download(String fname) {
		File file = new File(Path.FILE_STORE + fname);
		if(file.exists()) {
			return new ModelAndView("fileDownloadView", "downloadFile", file);
		}else {
			return new ModelAndView("redirect:list.do");
		}
	}
	
	
	void pln(String str) {
		System.out.println(str);
	}
}
