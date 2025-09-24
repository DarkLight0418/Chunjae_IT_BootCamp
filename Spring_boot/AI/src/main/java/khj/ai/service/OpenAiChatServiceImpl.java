package khj.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class OpenAiChatServiceImpl implements OpenAiChatService {
    @Value("${spring.ai.openai.api-key}")
    private String openAiApiKey;

    /*
    private final ChatClient client;

    public OpenAiChatServiceImpl(ChatClient client) {
        this.client = client;
    }
     */

    @Override
    public String getChatResponse(String message) {
        //pln("#OpenAiChatServiceImpl openAiApiKey: " + openAiApiKey);
        String url = "https://api.openai.com/v1/chat/completions";

        //(1)요청객체생성
        Map<String, Object> request = Map.of("model",  "gpt-3.5-turbo",
                "messages", new Object[]{Map.of("role", "user", "content", message)}
        );

        //(2) HttpEntity객체 생성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAiApiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        //(3) API호출
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        Map<String, Object> choice = (Map<String, Object>)((List<?>)response.getBody().get("choices")).get(0);
        Map<String, Object> messageObject = (Map<String, Object>)choice.get("message");
        String responseMsg = (String)messageObject.get("content");

        pln("#OpenAiChatServiceImpl AI의 답변: " + responseMsg);

        return responseMsg;
    }

    /*

    @Override
    public String getChatResponse(String message) {
        pln("#OpenAiChatServiceImpl openAiApiKey: " + openAiApiKey);
        String response = client.prompt(message).call().content();

        return response;
    }

     */

    void pln(String str){
        System.out.println(str);
    }
}
