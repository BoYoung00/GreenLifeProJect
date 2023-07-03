package com.example.greenlifeproject.config.auth;

import com.example.greenlifeproject.entity.MemberEntity;
import com.example.greenlifeproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

//login 으로 요청이 들어온다면 이 메서드가 실행됨
//정확히는 UserDetailsService 타입으로 Ioc 되어있는 loadUserByUsername 함수를 실행해줌
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberService memberService;
    //시큐리티 session = Authentication(내부 UserDetails)

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity member = memberService.findMemberEntityByEmail(username);

        if (member == null){
            throw new UsernameNotFoundException("사용자가 존재하지 않습니다.");
        }
        memberService.updateLoginTime(member);
        return new PrincipalDetails(member);//그걸 PrincipalDetails 로 씌워서 로그인해줌
    }

}
