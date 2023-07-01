package com.example.greenlifeproject.dto;

import com.example.greenlifeproject.constant.Role;
import com.example.greenlifeproject.entity.MemberEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;

@Data
@ToString
public class MemberDTO {

    private Long id;

    private String name;

    private String email;

    private String address;

    private String phoneNumber;

    private String birthDate;

    private Role role;

}
