package com.example.greenlifeproject.dto;


import com.example.greenlifeproject.constant.Role;
import com.example.greenlifeproject.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 50,unique = true)
    private String email;
    //가입 중복 방지를 위한 Unique 속성의 email 생성

    @Column(length = 50)
    private String password;

    @Column(length = 20)
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

    public static MemberEntity memberDtoToMemberEntity(MemberDTO memberDTO) {
        MemberEntity member = new MemberEntity();
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setPassword(memberDTO.getPassword());
        member.setAddress(memberDTO.getAddress());
        member.setPhoneNumber(memberDTO.getPhoneNumber());
        member.setBirthDate(memberDTO.getBirthDate());
        member.setRole(Role.USER);
        return member;
    }

}
