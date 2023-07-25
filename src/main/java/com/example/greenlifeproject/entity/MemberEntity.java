package com.example.greenlifeproject.entity;

import com.example.greenlifeproject.constant.Role;
import com.example.greenlifeproject.dto.MemberDTO;
import com.example.greenlifeproject.entity.DepressionTestResults.DepressionTestResultEntity;
import com.example.greenlifeproject.entity.chatEntitys.ChatEntity;
import com.example.greenlifeproject.entity.shopEntitys.OrderEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "MEMBER")
@Data
@ToString
public class MemberEntity extends BaseEntity{

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 50,unique = true)
    private String email;
    //가입 중복 방지를 위한 Unique 속성의 email 생성

    @Column(length = 100)
    private String password;

    @Column(length = 50)
    private String address;
    //쇼핑몰 배송지 주소를 받기 위한 주소 필드

    @Column(length = 20)
    private String phoneNumber;
    //쇼핑몰 배송자 전화번호를 받기 위한 전화번호

    @Column(length = 20)
    private String birthDate;
    //생년 월일

    @Enumerated(EnumType.STRING)
    private Role role;

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<ChatEntity> chatEntityList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member")
//    private List<OrderEntity> orders = new ArrayList<>();

    public static MemberEntity convertToMemberEntity(MemberDTO memberDTO) {
        MemberEntity member = new MemberEntity();

        member.setId(memberDTO.getId());
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setPassword(memberDTO.getPassword());
        member.setAddress(memberDTO.getAddress());
        member.setPhoneNumber(memberDTO.getPhoneNumber());
        member.setBirthDate(memberDTO.getBirthDate());
        member.setRole(Role.USER);//유저값으로 설정해둠

        return member;
    }


}
