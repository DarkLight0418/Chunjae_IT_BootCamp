package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "member_chat_room")
@NoArgsConstructor
public class MemberChatRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_chat_room_id")
    private Long memberChatRoomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.USER;

    public enum Role {
        USER, ADMIN
    }
}
