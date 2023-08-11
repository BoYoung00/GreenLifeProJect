package com.example.greenlifeproject.entity.chatEntitys;

import com.example.greenlifeproject.dto.ChatDTO;
import com.example.greenlifeproject.entity.BaseEntity;
import com.example.greenlifeproject.entity.MemberEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "CHAT")
@ToString
public class ChatEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "CHAT_ID")
    private Long id;

    @Column(name = "CHAT_TEXT")
    private String chatText;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private ChatRoomEntity chatRoom;



    public static ChatEntity convertToChatEntity(ChatDTO chatDTO){

        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setId(chatDTO.getId());
        chatEntity.setChatText(chatDTO.getContent());
        chatEntity.setMember(chatDTO.getMember());
        chatEntity.setChatRoom(chatDTO.getChatRoom());
        chatEntity.setCreateDataTime(LocalDateTime.now());

        return chatEntity;
    }
}
