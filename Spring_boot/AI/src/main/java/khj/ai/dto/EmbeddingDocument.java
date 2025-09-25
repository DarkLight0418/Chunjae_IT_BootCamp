package khj.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;


@AllArgsConstructor
@Data
public class EmbeddingDocument {
    private String text;
    List<Double> embedding;
}
