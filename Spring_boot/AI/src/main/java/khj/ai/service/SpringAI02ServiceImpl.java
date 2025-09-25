package khj.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpringAI02ServiceImpl implements SpringAI02Service {
    private final ImageModel imageModel;

    @Override
    public String generateImage(String prompt) {
        OpenAiImageOptions openAiImageOptions = OpenAiImageOptions.builder()
                .N(1) //이미지 갯수
                .width(1024) //가로
                .height(1024) //세로
                .build();

        //'1024x1024', '1024x1792', and '1792x1024'

        ImagePrompt imagePrompt = new ImagePrompt(prompt, openAiImageOptions);
        ImageResponse response = imageModel.call(imagePrompt);
        String imageUrl = response.getResult().getOutput().getUrl();
        pln("#SpringAI02ServiceImpl response: " + response);
        pln("#SpringAI02ServiceImpl imageUrl: " + imageUrl);

        return response.getResult().getOutput().getUrl();
    }

    void pln(String str){
        System.out.println(str);
    }
}
