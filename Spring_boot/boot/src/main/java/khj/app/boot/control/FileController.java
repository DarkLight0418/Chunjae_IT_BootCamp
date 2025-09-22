package khj.app.boot.control;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import khj.app.boot.domain.FileUp;
import khj.app.boot.service.FileService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("file")
@RequiredArgsConstructor
@Controller
public class FileController {
    private final FileService fileService;

    //(1) 파일업로드
    @GetMapping("upload.do")
    public String form(){
        return "file/form";
    }
    @PostMapping("upload.do")
    public String updateFile(@RequestParam("file") MultipartFile mf,
                             @RequestParam("files") List<MultipartFile> mfs) throws IOException  {
        long fileSize = mf.getSize();
        pln("@FileController fileSize: " + fileSize);
        fileService.saveFile(mf); //단일파일

        for(MultipartFile mfile: mfs){ //멀티파일
            fileService.saveFile(mfile);
        }

        return "redirect:list.do";
    }
    //(2) 파일리스팅
    @GetMapping("list.do")
    public String list(Model model){
        List<FileUp> fileUps = fileService.getFileUpAll();
        model.addAttribute("fileUps", fileUps);
        return "file/list";
    }

    @GetMapping("images/{id}")
    @ResponseBody
    public Resource getImageResource(@PathVariable("id") long id, Model model)
            throws IOException {
        FileUp fileUp = fileService.getFileUp(id);
        return new UrlResource("file:"+fileUp.getSavedpath());
    }

    //(3) 파일다운로드
    @GetMapping("attach/{id}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable("id") long id)
            throws IOException {
        FileUp fileUp = fileService.getFileUp(id);
        UrlResource resource = new UrlResource("file:"+fileUp.getSavedpath());
        String encodedFileName = UriUtils.encode(fileUp.getOrgnm(), StandardCharsets.UTF_8);

        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
    }

    /* //(3) 파일다운로드 by GPT
    @GetMapping("attach/{id}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable("id") long id) {
        FileUp fileUp = fileService.getFileUp(id);
        try {
            Path filePath = Paths.get(fileUp.getSavedpath()).normalize();
            UrlResource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "파일을 찾을 수 없거나 읽을 수 없습니다.");
            }

            String encodedFileName = UriUtils.encode(fileUp.getOrgnm(), StandardCharsets.UTF_8);
            String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"; filename*=UTF-8''" + encodedFileName;

            String mimeType = Files.probeContentType(filePath);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mimeType != null ? mimeType : MediaType.APPLICATION_OCTET_STREAM_VALUE))
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                    .body(resource);
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 경로가 올바르지 않습니다.");
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일을 처리하는 중 오류가 발생했습니다.");
        }
    }
*/

    void pln(String str){
        System.out.println(str);
    }
}
