package khj.md.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	String saveAtStore(MultipartFile file); //업로드된 파일이름(경로포함)을 리턴
}