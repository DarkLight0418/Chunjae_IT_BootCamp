package khj.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.audio.transcription.AudioTranscription;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class SpringAI04ServiceImpl implements SpringAI04Service {
    private final OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;

    @Override
    public String transcribe(MultipartFile audioFile) throws IOException {
        //(1) AudioTranscriptionPrompt 객체 생성
        OpenAiAudioApi.TranscriptResponseFormat responseFormat = OpenAiAudioApi.TranscriptResponseFormat.TEXT;

        OpenAiAudioTranscriptionOptions options = OpenAiAudioTranscriptionOptions.builder()
                .model("whisper-1")
                .responseFormat(responseFormat)
                .build();
        Resource audioResource = convertToResource(audioFile);
        AudioTranscriptionPrompt prompt = new AudioTranscriptionPrompt(audioResource, options);

        //(2) prompt를 이용해서 호출하고 결과 추출
        AudioTranscriptionResponse response = openAiAudioTranscriptionModel.call(prompt);
        AudioTranscription audioTranscription = response.getResult();
        String textResponse = audioTranscription.getOutput();

        return textResponse;
    }
    private Resource convertToResource(MultipartFile audioFile) throws IOException {
        return new ByteArrayResource(audioFile.getBytes()){
            @Override
            public String getFilename() {
                return audioFile.getOriginalFilename();
            }
        };
    }
}
