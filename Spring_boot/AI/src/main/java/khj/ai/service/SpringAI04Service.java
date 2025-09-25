package khj.ai.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface SpringAI04Service {
    String transcribe(MultipartFile audioFile) throws IOException;
}