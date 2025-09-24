package khj.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpringAI01ServiceImpl implements SpringAI01Service {
    private final OpenAiChatModel openAiChatModel;

    @Override
    public String getChatResponse(String message) {
        Prompt prompt = new Prompt(message);
        ChatResponse response = openAiChatModel.call(prompt);
        String responseMsg = response.getResult().getOutput().getText();
        return responseMsg;
    }
}
