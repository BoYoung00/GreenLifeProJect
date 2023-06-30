package com.example.greenlifeproject.entity.chatEntitys;

import com.example.greenlifeproject.entity.chatEntitys.ChatEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "CHAT_ROOM")
public class ChatRoomEntity {

    @Id
    @GeneratedValue
    @Column(name = "ROOM_ID")
    private Long id;

    @Column(name = "ROOM_NAME")
    private String roomName;

    //방 만든사람 ID
    @Column(name = "CREATOR_ID")
    private Long creatorId;

    //하나의 채팅방에 여러개의 채팅 주인은 charRoom 사라지면 같이 사라지게함
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<ChatEntity> chatEntityList = new ArrayList<>();
}
