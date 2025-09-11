package khj.md.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import khj.md.fileset.path.Path;
import java.io.*;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	@Override
	public String saveAtStore(MultipartFile file) {
		String ofname = file.getOriginalFilename();
		int idx = ofname.lastIndexOf(".");
		String ofheader = ofname.substring(0, idx); 
		String ext = ofname.substring(idx);
		long ms = System.currentTimeMillis();
		
		StringBuilder sb = new StringBuilder();
		sb.append(ofheader);
		sb.append("_");
		sb.append(ms);
		sb.append(ext);
		String saveFileName = sb.toString(); 
		
		long fsize = file.getSize();
		//pln("#ofname: " + ofname + ", saveFileName: "+ saveFileName + ", fsize: " + fsize);
		
		boolean flag = writeFile(file, saveFileName);
		if(flag) {
			pln("@업로드 성공");
		}else {
			pln("@업로드 실패");
		}
		
		return Path.FILE_STORE + saveFileName;
	}
	private boolean writeFile(MultipartFile file, String saveFileName) {
		File dir = new File(Path.FILE_STORE);
		if(!dir.exists()) dir.mkdirs();
		
		FileOutputStream fos = null;
		try {
			byte data[] = file.getBytes();
			fos = new FileOutputStream(Path.FILE_STORE + saveFileName);
			fos.write(data);
			fos.flush();
			
			return true;
		}catch(IOException ie) {
			return false;
		}finally {
			try {
				if(fos != null) fos.close();
			}catch(IOException ie) {}
		}
		
	}
	
	private void pln(String str) {
		System.out.println(str);
	}
}
