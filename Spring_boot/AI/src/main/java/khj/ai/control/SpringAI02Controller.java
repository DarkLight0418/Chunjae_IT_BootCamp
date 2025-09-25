package khj.ai.control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import khj.ai.service.SpringAI02Service;

@RequiredArgsConstructor
@RequestMapping("springai")
@Controller
public class SpringAI02Controller {
    @GetMapping("image.do")
    public String image() {
        //return "springai/image";
        return "springai/image_merge";
    }

    private final SpringAI02Service springAI02Service;
    @PostMapping("image.do")
    public String generateImage(@RequestParam("prompt") String prompt, Model model){
        String imageUrl = springAI02Service.generateImage(prompt);
        model.addAttribute("imageUrl", imageUrl);
        //return "springai/image_result";
        return "springai/image_merge";
    }
}