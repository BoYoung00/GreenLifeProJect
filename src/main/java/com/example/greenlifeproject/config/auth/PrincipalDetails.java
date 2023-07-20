package com.example.greenlifeproject.config.auth;

//login 으로 로그인 경로가 들어오면
//로그인을 진행이 완료되면 session 값을 발급해주어야함
//근데 시큐리티는 시큐리티 세션을 가지고 있음 (Security ContextHolder)라는 값
//저안에 들어가는 정보는 오브젝트가 정해져있음 => Authentication 객체 여야함
//Authentication 안에는 User 정보가 있어야함
//이 클래스도 타입이 정해져있음 UserDetails 타입의 객체여야만함

import com.example.greenlifeproject.entity.MemberEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

//Security Session 안에 => Authentication => UserDetails(PrincipalDetails);
public class PrincipalDetails implements UserDetails, OAuth2User {

    private MemberEntity user; //콤 포지션

    private Map<String,Object> attributes;
    public PrincipalDetails(MemberEntity user){
        this.user = user;
        //일반 유저가 접속하면 실행되는 메소드
    }

    public PrincipalDetails(MemberEntity user , Map<String,Object> attributes){
        this.user = user;
        this.attributes = attributes;
        //OAuth 로그인
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return String.valueOf(user.getRole());
            }
        });
        return collection;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        //계정이 만료되었는지 확인 (true) 아니요
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //잠겨있는지
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //기간
        return true;
    }

    @Override
    public boolean isEnabled() {
        //계정 활성화 여부
        //우리 사이트에서 1년동안 회원이 로그인을 한 기록이 없으면 휴먼처리한다고함
        //현재시간 - 로그인 시간
        return true;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return user.getName();
    }
}
