package khj.ai;

import io.micrometer.observation.ObservationRegistry;
import khj.ai.service.SpringAI03Service;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.moderation.ModerationModel;
import org.springframework.ai.openai.*;
//import org.springframework.ai.embedding.EmbeddingModel;
//import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.ai.openai.api.OpenAiModerationApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;
import khj.ai.service.SpringAI03ServiceImpl;

@Configuration
public class OpenAiConfig {
    @Value("${spring.ai.openai.api-key}")
    private String openAiApiKey;

    @Bean
    public OpenAiChatModel openAiChatModel(){
        //(1) OpenAiApi 객체 생성
        OpenAiApi openAiApi = OpenAiApi.builder().apiKey(openAiApiKey).build();

        //(2) 기본 채팅 옵션 객체 생성
        OpenAiChatOptions defaultOptions = OpenAiChatOptions.builder()
                .model("gpt-3.5-turbo")
                .temperature(1.2) //0.0<=val<=2.0
                .build();

        //(3) ToolCallingManager 객체 생성
        ToolCallingManager toolCallingManager = ToolCallingManager.builder().build();

        //(4) RetryTemplate 객체 생성 //작업을 최대 3번 시도하며, 실패할 경우 매번 1초씩 대기 후 재시도한다
        RetryTemplate retryTemplate = RetryTemplate.builder()
                .maxAttempts(3)
                .fixedBackoff(1000)
                .build();

        //(5) ObservationRegistry 객체 생성
        ObservationRegistry observationRegistry = ObservationRegistry.NOOP; //관측안함

        //(6) 전체 모델 생성
        OpenAiChatModel openAiChatModel = new OpenAiChatModel(
                openAiApi,
                defaultOptions,
                toolCallingManager,
                retryTemplate,
                observationRegistry
        );

        return openAiChatModel;
    }

    @Bean
    public OpenAiImageModel openAiImageModel() {
        OpenAiImageApi openAiImageApi = OpenAiImageApi.builder()
                .apiKey(openAiApiKey)
                .build();

        return new OpenAiImageModel(openAiImageApi);
    }

    @Bean
    public OpenAiAudioSpeechModel openAiAudioSpeechModel() {
        OpenAiAudioApi openAiAudioApi = OpenAiAudioApi.builder()
                .apiKey(openAiApiKey).build();

        return new OpenAiAudioSpeechModel(openAiAudioApi);
    }

    @Bean
    public SpringAI03Service springAI03Service(OpenAiAudioSpeechModel model) {
        return new SpringAI03ServiceImpl(model);
    }

    @Bean
    public OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel() {
        OpenAiAudioApi openAiAudioApi = OpenAiAudioApi.builder().apiKey(openAiApiKey).build();

        return new OpenAiAudioTranscriptionModel(openAiAudioApi);
    }

    @Bean
    public EmbeddingModel EmbeddingModel() {
        OpenAiApi openAiApi = OpenAiApi.builder().apiKey(openAiApiKey).build();
        return new OpenAiEmbeddingModel(openAiApi);
    }

    @Bean
    public ModerationModel moderationModel() {
        OpenAiModerationApi openAiApi = OpenAiModerationApi.builder().apiKey(openAiApiKey).build();
        return new OpenAiModerationModel(openAiApi);
    }
}
