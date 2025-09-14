package khj.board.service;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import khj.md.fileset.path.Path;

public class FileService {
	public void deleteFile(String fname) {
		File file = new File(Path.FILE_STORE + fname);
		if (file.exists()) file.delete();
	}
	
	public String saveAtStore(MultipartFile file) {
		String ofname = file.getOriginalFilename();
		if (ofname == null || ofname.trim().isEmpty()) return null;
		
		File dest = new File(Path.FILE_STORE, ofname);
		try {
			file.transferTo(dest);
		} catch (IOException ie) {
			System.out.println("저장 예외 발생 : " + ie);
		}
		return dest.getAbsolutePath();
	}
}
