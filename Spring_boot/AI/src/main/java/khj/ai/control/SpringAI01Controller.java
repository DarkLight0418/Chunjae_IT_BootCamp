package khj.ai.control;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import khj.ai.service.SpringAI01Service;

@AllArgsConstructor
@Controller
@RequestMapping("springai")
public class SpringAI01Controller {
    @GetMapping("chat.do")
    public String chat(){
        return "springai/chat";
    }

    private final SpringAI01Service springAI01Service;
    @PostMapping("chat.do")
    public String chat(@RequestParam("message") String message, Model model){
        String response = springAI01Service.getChatResponse(message);
        model.addAttribute("message", message);
        model.addAttribute("response", response);

        return "springai/chat";
    }
}
