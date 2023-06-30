package com.example.greenlifeproject.entity.chatEntitys;

import com.example.greenlifeproject.entity.MemberEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name = "CHAT")
@ToString
public class ChatEntity {
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

}
