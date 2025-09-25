package khj.ai.control;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import khj.ai.service.SpringAI03Service;

@AllArgsConstructor
@Controller
@RequestMapping("springai")
public class SpringAI03Controller {
    @GetMapping("speech.do")
    public String tta(){
        return "springai/speech";
    }

    private final SpringAI03Service springAI03Service;  // 추가
    @PostMapping("speech.do")
    public ResponseEntity<byte[]> tta(@RequestParam("text") String text){
        byte[] audioContent = springAI03Service.convertTextToSpeech(text);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "speech.mp3");

        return new ResponseEntity<>(audioContent, headers, HttpStatus.OK);
    }
}
