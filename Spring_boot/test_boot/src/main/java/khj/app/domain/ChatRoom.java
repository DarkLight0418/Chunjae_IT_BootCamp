package khj.app.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "chat_room")
public class ChatRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long chatRoomId;

    @Column(name = "room_name")
    private String roomName;

    @OneToMany(mappedBy = "chatRoom")
    private List<MemberChatRoom> memberChatRooms = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom")
    private List<Chat> chats = new ArrayList<>();
}
