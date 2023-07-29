package com.example.greenlifeproject.dto;

import com.example.greenlifeproject.entity.MemberEntity;
import com.example.greenlifeproject.entity.chatEntitys.ChatRoomEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
public class ChatDTO {

    private Long id;

    private String content;

    private MemberEntity member;

    private ChatRoomEntity chatRoom;

}
