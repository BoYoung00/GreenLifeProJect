package com.example.greenlifeproject.service;

import com.example.greenlifeproject.dto.MemberDTO;
import com.example.greenlifeproject.entity.MemberEntity;
import com.example.greenlifeproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    public void addNewMember(MemberDTO memberDTO){
        MemberEntity member = MemberDTO.memberDtoToMemberEntity(memberDTO);
        member.setPassword(setPasswordEncoder(member.getPassword()));

        memberRepository.save(member);
    }
    public String setPasswordEncoder(String password){

        return passwordEncoder.encode(password);
    }

    public boolean existsByEmail(String email) {
        MemberEntity member = memberRepository.findByEmail(email);

        return member != null;
    }

}
