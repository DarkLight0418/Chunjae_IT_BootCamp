package khj.app.boot.control;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import khj.app.boot.fileset.Path;
import khj.app.boot.service.DragdropService;
import java.io.File;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RequestMapping("drag_drop")
@Controller
public class DragdropController {
    private final DragdropService dragdropService;

    @GetMapping("form.do")
    public String form() {
        return "drag_drop/form";
    }
    @PostMapping("upload.do")
    public String fileUpload(MultipartHttpServletRequest mhsr) {
        File fStore = new File(Path.FILE_STORE);
        if(!fStore.exists()) fStore.mkdirs();

        dragdropService.setMultipartHttpServletRequest(mhsr);
        Map<String, List<Object>> map = dragdropService.getUploadFileName();
        pln("@DragdropController map: " + map);

        String appendData = mhsr.getParameter("temp");
        pln("@DragdropController appendData: " + appendData);

        return "redirect:list.do";
    }
    @GetMapping("list.do")
    public ModelAndView fileList() {
        File fStore = new File(Path.FILE_STORE);
        if(!fStore.exists()) fStore.mkdirs();
        File files[] = fStore.listFiles();

        return new ModelAndView("drag_drop/list", "files", files);
    }
    @GetMapping("del.do")
    public String del(String fname) {
        File file = new File(Path.FILE_STORE + fname);
        if(file.exists()) file.delete();

        return "redirect:list.do";
    }

    void pln(String str) {
        System.out.println(str);
    }
}
