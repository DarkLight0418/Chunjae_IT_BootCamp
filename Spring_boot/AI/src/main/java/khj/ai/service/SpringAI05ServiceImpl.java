package khj.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SpringAI05ServiceImpl implements SpringAI05Service {
    private final EmbeddingModel embeddingModel;

    @Override
    public List<Double> getEmbeddings(String text) {
        //(1) EmbeddingRequest 객체 생성
        List<String> inputs = List.of(text);
        OpenAiEmbeddingOptions options = OpenAiEmbeddingOptions.builder()
                .model("text-embedding-ada-002") //OpenAI의 기본 임베딩 모델
                //.dimensions(1536) //ada 임베팅 차원( 여기선 주석처리 )
                .encodingFormat("float") //보통 float사용
                //.user("user-6")
                .user("demo-user")  //사용자식별(선택)
                .build();
        EmbeddingRequest request = new EmbeddingRequest(inputs, options);

        //(2) request객체로 호출 결과값을 추출
        EmbeddingResponse response =  embeddingModel.call(request);

        float floats[] = response.getResults().get(0).getOutput(); //테스트1
        //float floats[] = response.getResult().getOutput(); //테스팅2

        //(3) List<Double> 형태로 변경
        List<Double> list = new ArrayList<>();
        for(float f: floats){
            Double d = Double.valueOf(f);
            list.add(d);
        }

        return list;
    }
}
