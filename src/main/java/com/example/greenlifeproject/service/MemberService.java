package com.example.greenlifeproject.service;

import com.example.greenlifeproject.dto.MemberDTO;
import com.example.greenlifeproject.entity.MemberEntity;
import com.example.greenlifeproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    public MemberEntity saveMember(MemberDTO memberDTO){
        MemberEntity member = MemberDTO.convertToMemberEntity(memberDTO);

        validateDuplicateMember(member);//이메일 중복 검사

        String password = member.getPassword();
        member.setPassword(passwordEncoder(passwordEncoder,password));

        return memberRepository.save(member);
    }
    public void updateLoginTime(MemberEntity member){
        member.setUpdatedTime(LocalDateTime.now());//멤버의 로그인 시간을 현재로 바꿈

        memberRepository.save(member);
    }
    public String passwordEncoder(PasswordEncoder passwordEncoder,String password){
        return passwordEncoder.encode(password);
    }

    private void validateDuplicateMember(MemberEntity member){
        //아이디 중복값을 확인하는 메소드
        MemberEntity memberEntity = memberRepository.findByEmail(member.getEmail());

        if (memberEntity != null){
            throw new IllegalStateException("이미 가입된 회원입니다");
        }
    }

    public MemberEntity findMemberEntityByEmail(String email){

        return  memberRepository.findByEmail(email);
    }

    public MemberEntity findMemberEntityByID(Long id){

        return memberRepository.findById(id).orElse(null);
    }
}
