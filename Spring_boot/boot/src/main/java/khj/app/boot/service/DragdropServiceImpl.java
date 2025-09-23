package khj.app.boot.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import khj.app.boot.fileset.Path;

@Service
public class DragdropServiceImpl implements DragdropService {
    private MultipartHttpServletRequest multipartHttpServletRequest;
    @Override
    public MultipartHttpServletRequest getMultipartHttpServletRequest() {
        return multipartHttpServletRequest;
    }
    @Override
    public void setMultipartHttpServletRequest(MultipartHttpServletRequest multipartHttpServletRequest) {
        this.multipartHttpServletRequest = multipartHttpServletRequest;
    }

    private Map<String, List<Object>> map;
    @Override
    public Map<String, List<Object>> getUploadFileName() {
        upload();
        return map;
    }
    private void upload() {
        Iterator<String> itr = multipartHttpServletRequest.getFileNames();

        map = new Hashtable<String, List<Object>>();
        List<Object> ofnames = new ArrayList<Object>();
        List<Object> savefnames = new ArrayList<Object>();
        List<Object> fsizes = new ArrayList<Object>();

        while(itr.hasNext()) {
            StringBuffer sb = new StringBuffer();
            MultipartFile mpf = multipartHttpServletRequest.getFile(itr.next());
            String ofname = mpf.getOriginalFilename();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            String ctm = sdf.format(System.currentTimeMillis());
            sb.append(ctm)
                    .append(UUID.randomUUID().toString())
                    .append(ofname.substring(ofname.lastIndexOf(".")));

            String savefname = sb.toString();
            long fsize = mpf.getSize();
            String filePullPath = Path.FILE_STORE+savefname;
            try {
                mpf.transferTo(new File(filePullPath));
                ofnames.add(ofname);
                savefnames.add(savefname);
                fsizes.add(fsize);
            }catch(IOException ie) {
                System.out.println("#DragdropServiceImpl upload() ie: " + ie);
            }
        }

        map.put("ofnames", ofnames);
        map.put("savefnames", savefnames);
        map.put("fsizes", fsizes);
    }
}

