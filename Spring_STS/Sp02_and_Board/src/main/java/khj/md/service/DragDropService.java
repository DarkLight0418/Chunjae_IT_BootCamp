package khj.md.service;

import java.util.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface DragDropService {
	Map<String, List<Object>> getUploadFileName(); //키: ofnames, savefnames, fsizes
	
	MultipartHttpServletRequest getMultipartHttpServletRequest();
	void setMultipartHttpServletRequest(MultipartHttpServletRequest multipartHttpServletRequest);
}