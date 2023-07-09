package com.example.greenlifeproject.config.auth.oath;

import com.example.greenlifeproject.config.auth.PrincipalDetails;
import com.example.greenlifeproject.constant.Role;
import com.example.greenlifeproject.dto.MemberDTO;
import com.example.greenlifeproject.entity.MemberEntity;
import com.example.greenlifeproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PrincipalOath2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final HttpSession httpSession;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        printOAuth2Information(userRequest);
        // 회원가입을 강제로 진행
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getClientId(); // Google 리턴
        String providerID = oAuth2User.getAttribute("sub");
        String email = oAuth2User.getAttribute("email");
        String username = provider + "_" + providerID + "_" + oAuth2User.getAttribute("name");
        String password = "password";
        Role role = Role.USER;

        MemberEntity member = memberRepository.findByName(username);

        if (member == null){
            MemberDTO memberDTO = MemberDTO.builder()
                    .name(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .build();

            member = MemberDTO.convertToMemberEntity(memberDTO);

            httpSession.setAttribute("memberDTO",memberDTO);

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

            try {
                redirectStrategy.sendRedirect(request, response, "/members/join?memberDTO");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return new PrincipalDetails(member, oAuth2User.getAttributes());
    }

    public void printOAuth2Information(OAuth2UserRequest userRequest) {
        System.out.println("USER Request: " + userRequest);
        System.out.println("getAccessToken: " + userRequest.getAccessToken());
        System.out.println("getAdditionalParameters: " + userRequest.getAdditionalParameters());
        System.out.println("getClientRegistration: " + userRequest.getClientRegistration()); // 어떤 Oauth로 로그인 했는지 확인
    }

}
