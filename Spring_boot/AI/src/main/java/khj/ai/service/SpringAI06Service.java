package khj.ai.service;

import org.springframework.ai.moderation.ModerationResult;

public interface SpringAI06Service {
    ModerationResult moderateText(String input);
}
