package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "chat")
@NoArgsConstructor
public class Chat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
