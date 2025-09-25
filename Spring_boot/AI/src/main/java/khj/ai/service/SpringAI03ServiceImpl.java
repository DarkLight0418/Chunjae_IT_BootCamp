package khj.ai.service;

import khj.ai.service.SpringAI03Service;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpringAI03ServiceImpl implements SpringAI03Service {
    private final OpenAiAudioSpeechModel openAiAudioSpeechModel;

    @Override
    public byte[] convertTextToSpeech(String text) {
        OpenAiAudioSpeechOptions speechOptions = OpenAiAudioSpeechOptions.builder()
                .model("tts-1")
                .voice(OpenAiAudioApi.SpeechRequest.Voice.ALLOY)
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .speed(1.0f)
                .build();

        SpeechPrompt speechPrompt = new SpeechPrompt(text, speechOptions);
        SpeechResponse response = openAiAudioSpeechModel.call(speechPrompt);

        return response.getResult().getOutput();
    }
}
