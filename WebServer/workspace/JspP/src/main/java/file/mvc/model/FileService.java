package file.mvc.model;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.*;

import file.mvc.control.FileController;
import file.mvc.fileset.Path;

public class FileService {
	//private FileDAO dao; //DB사용시만 
	
	private static final FileService instance = new FileService();
	private FileService() {
		//dao = new FileDAO(); //DB사용시만
	}
	public static FileService getInstance() {
		return instance;
	}
	public boolean saveFile(Part filePart) {
		String fileName = getSubmittedFileName(filePart);
		if(fileName != null) fileName = fileName.trim();
		System.out.println("#fileName: " + fileName);
		if(fileName.length() == 0) {
			return false;
		}
		
		long fSize = filePart.getSize();
		long maxFileSize = FileController.class.getAnnotation(MultipartConfig.class).maxFileSize();
		System.out.println("#fSize: " + fSize + ", maxFileSize: " + maxFileSize);
		
		String uploadPath = Path.FILE_STORE;
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		File saveFile = new File(uploadDir, fileName); 
		saveFile = duplicateCheck(saveFile, fileName); 
		
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			is = filePart.getInputStream(); //Source 
			fos = new FileOutputStream(saveFile); //Destination 
			byte bs[] = new byte[512];
			int i = 0;
			while((i = is.read(bs)) != -1) {
				fos.write(bs, 0, i);
			}
			fos.flush();
			
			return true;
		}catch(IOException ie) {
			return false;
		}finally {
			try {
				if(is != null) is.close();
				if(fos != null) fos.close();
			}catch(IOException ie) {}
		}
	}
	private String getSubmittedFileName(Part part) {
		String header = part.getHeader("content-disposition");
		for(String cd : header.split(";")) {
			if(cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
			}
		}
		return null;
	}
	private File duplicateCheck(File saveFile, String fileName) {
		String fNameNoExtension = "";
		String extension = "";
				
		int idx = fileName.lastIndexOf(".");		
		if(idx>0) {
			fNameNoExtension = fileName.substring(0, idx);
			extension = fileName.substring(idx);			
		}else {
			fNameNoExtension = fileName;
		}
		
		int count = 1;		
		while(saveFile.exists()) {
	        saveFile = new File(Path.FILE_STORE, fNameNoExtension + "(" + count + ")" + extension);
	        count++;
		}
		
		return saveFile;
	}
	public void download(HttpServletRequest request, HttpServletResponse response, File f) {
		try {
			String fname = f.getName();
			response.setContentType("application/octet-stream"); 
			String Agent=request.getHeader("USER-AGENT");
			if( Agent.indexOf("MSIE") >= 0 ){
				int i = Agent.indexOf( 'M', 2 );
				String IEV = Agent.substring( i + 5, i + 8 );
				if( IEV.equalsIgnoreCase("5.5") ){
					response.setHeader("Content-Disposition", "filename=" + new String( fname.getBytes("utf-8"), "8859_1") );
				}else{
					response.setHeader("Content-Disposition", "attachment;filename="+new String(fname.getBytes("utf-8"),"8859_1"));
				}
			}else{
				response.setHeader("Content-Disposition", "attachment;filename=" + new String( fname.getBytes("utf-8"), "8859_1") );
			}

			if(f.exists() && f.isFile()) {
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				OutputStream os = null;
				BufferedOutputStream bos = null;
				try {
					fis = new FileInputStream(f);
					bis = new BufferedInputStream(fis, 2048);
					os = response.getOutputStream();
					bos = new BufferedOutputStream(os, 2048);
					
					int i = 0; 
					byte b[] = new byte[1024];
					while((i=bis.read(b)) != -1) {
						bos.write(b, 0, i);
					}
					bos.flush();
				}catch(IOException ie) {
				}finally {
					try {
						if(bos != null) bos.close();
						if(bis != null) bis.close();
						if(fis != null) fis.close();
						if(os != null) os.close();
					}catch(IOException ie) {}
				}
			}
		}catch(UnsupportedEncodingException ue) {
		}
	}
}
