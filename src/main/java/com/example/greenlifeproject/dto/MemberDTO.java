package com.example.greenlifeproject.dto;

import com.example.greenlifeproject.constant.Role;
import com.example.greenlifeproject.entity.DepressionTestResults.DepressionTestResultEntity;
import com.example.greenlifeproject.entity.MemberEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class MemberDTO {

    private Long id;
    
    @NotBlank(message = "이름은 필수값 입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수값 입니다.")
    private String email;

    @NotEmpty(message = "주소는 필수값 입니다.")
    private String address;

    @NotEmpty(message = "비밀번호는 필수값 입니다.")
    private String password;

    @NotEmpty(message = "휴대폰 번호는 필수값 입니다.")
    private String phoneNumber;

    @NotEmpty(message = "생년 월일은 필수값 입니다.")
    private String birthDate;
    
    private Role role;

    @Builder
    public MemberDTO(Long id, String name, String email, String address, String password, String phoneNumber, String birthDate, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.role = role;
    }

    public static MemberDTO convertToMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setName(memberEntity.getName());
        memberDTO.setEmail(memberEntity.getEmail());
        memberDTO.setPassword(memberEntity.getPassword());
        memberDTO.setAddress(memberEntity.getAddress());
        memberDTO.setBirthDate(memberEntity.getBirthDate());
        memberDTO.setPhoneNumber(memberEntity.getPhoneNumber());
        memberDTO.setRole(memberEntity.getRole());

        return memberDTO;
    }
}
