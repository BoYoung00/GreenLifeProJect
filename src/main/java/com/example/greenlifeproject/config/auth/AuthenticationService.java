package com.example.greenlifeproject.config.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public String getCurrentUser(Authentication authentication){

        if (authentication.getPrincipal() instanceof PrincipalDetails) {
            //일반 로그인으로 넘겨주기

            return (((PrincipalDetails) authentication.getPrincipal()).getName());
        } else if (authentication.getPrincipal() instanceof OAuth2User) {

            return ((OAuth2User) authentication.getPrincipal()).getName();
        } else
            return null;
    }

    public String getCurrentUserEmail(Authentication authentication){

        if (authentication.getPrincipal() instanceof PrincipalDetails) {
            //일반 로그인으로 넘겨주기
            return (((PrincipalDetails) authentication.getPrincipal()).getUsername());
        } else if (authentication.getPrincipal() instanceof OAuth2User) {
            //소셜 로그인으로 처리하여 넘겨주기
            return ((OAuth2User) authentication.getPrincipal()).getAttribute("Email");
        } else
            return null;
    }

}
