package khj.ai.control;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import khj.ai.service.OpenAiChatService;

@AllArgsConstructor
@Controller
@RequestMapping("openai")
public class OpenAiChatController {
    private final OpenAiChatService openAiChatService;

    @GetMapping("chat.do")
    public String chat(){
        return "openai/chat";
    }
    @PostMapping("chat.do")
    public String chat(@RequestParam("message") String message, Model model){
        String response = openAiChatService.getChatResponse(message);
        //pln("@OpenAiChatController(give) message: " + message);
        //pln("@OpenAiChatController(take) response: " + response);
        model.addAttribute("message", message);
        model.addAttribute("response", response);

        return "openai/chat";
    }

    void pln(String str){
        System.out.println(str);
    }
}
