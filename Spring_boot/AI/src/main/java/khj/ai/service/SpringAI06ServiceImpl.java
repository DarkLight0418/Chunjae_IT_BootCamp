package khj.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.moderation.*;
import org.springframework.ai.openai.OpenAiModerationOptions;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SpringAI06ServiceImpl implements SpringAI06Service {
    private final ModerationModel moderationModel;

    @Override
    public ModerationResult moderateText(String input) {
        //(1) prompt 객체 생성
        OpenAiModerationOptions moderationOptions = OpenAiModerationOptions.builder()
                .model("text-moderation-latest")
                .build();
        ModerationPrompt prompt = new ModerationPrompt(input, moderationOptions);

        //(2) prompt 객체로 호출해서 결과 추출
        ModerationResponse response = moderationModel.call(prompt);
        //Generation g = response.getResults().get(0);
        Generation g = response.getResult(); //상동

        //(3) ModerationResult형태로 변경해서 리턴
        Moderation moderation =  g.getOutput();
        pln("#SpringAI06ServiceImpl moderationID: " + moderation.getId());
        pln("#SpringAI06ServiceImpl usedModel: " + moderation.getModel());
        List<ModerationResult> moderationResults = moderation.getResults();

        return moderationResults.get(0);
    }

    void pln(String str){
        System.out.println(str);
    }
}

