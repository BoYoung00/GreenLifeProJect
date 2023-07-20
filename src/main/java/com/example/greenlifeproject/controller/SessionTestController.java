package com.example.greenlifeproject.controller;

import com.example.greenlifeproject.config.auth.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class SessionTestController {

    @GetMapping("/login/session/test")
    public @ResponseBody String loginTest(Authentication authentication) {
        if (authentication.getPrincipal() instanceof PrincipalDetails) {
            //여기서 바로 메인 페이지로 이동
            System.out.println(((PrincipalDetails) authentication.getPrincipal()).getName());
            return "/";
        } else if (authentication.getPrincipal() instanceof OAuth2User) {
            //회원가입으로 이동?
            System.out.println(((OAuth2User) authentication.getPrincipal()).getName());
            System.out.println("구글 로그인 입니다");
        } else {
            System.out.println("인증 정보를 확인할 수 없습니다");
        }
        return "예외처리";
    }

}
