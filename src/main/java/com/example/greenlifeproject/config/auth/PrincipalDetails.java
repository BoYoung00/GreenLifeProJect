package com.example.greenlifeproject.config.auth;

//login 으로 로그인 경로가 들어오면
//로그인을 진행이 완료되면 session 값을 발급해주어야함
//근데 시큐리티는 시큐리티 세션을 가지고 있음 (Security ContextHolder)라는 값
//저안에 들어가는 정보는 오브젝트가 정해져있음 => Authentication 객체 여야함
//Authentication 안에는 User 정보가 있어야함
//이 클래스도 타입이 정해져있음 UserDetails 타입의 객체여야만함

import com.example.greenlifeproject.entity.MemberEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collection;

//Security Session 안에 => Authentication => UserDetails(PrincipalDetails);
public class PrincipalDetails implements UserDetails {

    private MemberEntity user; //콤 포지션

    public PrincipalDetails(MemberEntity user){
        this.user = user;
    }

    //해당 User 의 권한을 리턴하는 공간

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
        return user.getName();
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
}
