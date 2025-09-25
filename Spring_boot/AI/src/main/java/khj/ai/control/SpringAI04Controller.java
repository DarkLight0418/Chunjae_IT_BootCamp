package khj.ai.control;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import khj.ai.service.SpringAI04Service;
import java.io.IOException;

@AllArgsConstructor
@Controller
@RequestMapping("springai")
public class SpringAI04Controller {
    @GetMapping("transcription.do")
    public String att(){
        return "springai/transcription";
    }

    private final SpringAI04Service springAI04Service;
    @PostMapping("transcription.do")
    public @ResponseBody String att(@RequestParam("audioFile") MultipartFile audioFile){
        try {
            String transcription = springAI04Service.transcribe(audioFile);
            pln("@SpringAI04Controller transcription: " + transcription);

            return transcription;
        }catch(IOException ie){
            String err = "@SpringAI04Controller 오류발생: " + ie;
            pln(err);

            return err;
        }
    }

    void pln(String str){
        System.out.println(str);
    }
}
