package com.example.greenlifeproject.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.lang.reflect.Member;

@Entity
@Data
@Table(name = "CHAT")
@ToString
public class ChatEntity {
    @Id
    @GeneratedValue
    @Column(name = "CHAT_ID")
    private Long id;

    private String chatText;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity member;
}
