package khj.ai.control;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import khj.ai.dto.EmbeddingDocument;
import khj.ai.service.SpringAI05Service;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("springai")
public class SpringAI05Controller {
    @GetMapping("embedding.do")
    public String embed(){
        return "springai/embedding";
    }

    private final SpringAI05Service springAI05Service;
    @PostMapping("embedding.do")
    public @ResponseBody EmbeddingDocument embed(@RequestParam("text") String text){
        List<Double> embeddings = springAI05Service.getEmbeddings(text);
        pln("@SpringAI05Controller embeddings: " + embeddings);
        pln("@SpringAI05Controller embeddings.size: " + embeddings.size());
        EmbeddingDocument embeddingDocument = new EmbeddingDocument(text, embeddings);
        return embeddingDocument;
    }

    void pln(String str){
        System.out.println(str);
    }
}
