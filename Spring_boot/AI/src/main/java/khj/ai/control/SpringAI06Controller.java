package khj.ai.control;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.moderation.ModerationResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import khj.ai.service.SpringAI06Service;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("springai")
@Controller
public class SpringAI06Controller {
    @GetMapping("moderation.do")
    public String moderate(){
        return "springai/moderation";
    }

    private final SpringAI06Service springAI06Service;
    @PostMapping("moderation.do")
    public @ResponseBody Map<String, Object> moderate(@RequestParam("input") String input){
        ModerationResult moderationResult = springAI06Service.moderateText(input);
        pln("@SpringAI06Controller moderationResult: " + moderationResult);

        return Map.of(
                "flagged", moderationResult.isFlagged(),
                "categories", moderationResult.getCategories(),
                "categoryScores", moderationResult.getCategoryScores()
        );
    }

    void pln(String str){
        System.out.println(str);
    }
}
