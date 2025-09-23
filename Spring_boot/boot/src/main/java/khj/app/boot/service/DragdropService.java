package khj.app.boot.service;

import java.util.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface DragdropService {
    Map<String, List<Object>> getUploadFileName(); //키: ofnames, savefnames, fsizes

    MultipartHttpServletRequest getMultipartHttpServletRequest();
    void setMultipartHttpServletRequest(MultipartHttpServletRequest multipartHttpServletRequest);
}
