package com.example.greenlifeproject.config.auth.oath;

import com.example.greenlifeproject.config.auth.PrincipalDetails;
import com.example.greenlifeproject.config.auth.oath.provider.FacebookUserInfo;
import com.example.greenlifeproject.config.auth.oath.provider.GoogleUserInfo;
import com.example.greenlifeproject.config.auth.oath.provider.NaverUserInfo;
import com.example.greenlifeproject.config.auth.oath.provider.OAuth2UserInfo;
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
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrincipalOath2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final HttpSession httpSession;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        printOAuth2Information(userRequest);

        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = getOAuth2UserInfo(oAuth2User,userRequest);

        String username = createMemberName(oAuth2UserInfo,userRequest);

        MemberEntity member = memberRepository.findByName(username);

        if (member == null){
            MemberDTO memberDTO = builderMemberDTO(username,oAuth2UserInfo);
            member = MemberEntity.convertToMemberEntity(memberDTO);

            //세션에 memberDTO 값을 저장
            storeMemberDTOInSession(memberDTO);

            redirectJoinPage();
        }

        return new PrincipalDetails(member, oAuth2User.getAttributes());
    }

    public OAuth2UserInfo getOAuth2UserInfo(OAuth2User oAuth2User, OAuth2UserRequest userRequest) {
        OAuth2UserInfo oAuth2UserInfo = null;

        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            oAuth2UserInfo = new NaverUserInfo((Map<String, Object>) oAuth2User.getAttributes().get("response"));
        } else {
            System.out.println("아직 해당 로그인은 지원하지 않습니다.");
        }

        return oAuth2UserInfo;
    }

    private String createMemberName(OAuth2UserInfo oAuth2UserInfo , OAuth2UserRequest userRequest){
        String provider = oAuth2UserInfo.getProvider();
        String providerID = oAuth2UserInfo.getProviderId();

        return provider + "_" + providerID + "_" + oAuth2UserInfo.getName();
    }
    private MemberDTO builderMemberDTO(String username,OAuth2UserInfo oAuth2UserInfo){
        String email = oAuth2UserInfo.getEmail();
        String password = "password";
        Role role = Role.USER;

        return MemberDTO.builder()
                .name(username)
                .password(password)
                .email(email)
                .role(role)
                .build();
    }

    private void storeMemberDTOInSession(MemberDTO memberDTO) {
        httpSession.setAttribute("memberDTO", memberDTO);
    }

    private void redirectJoinPage(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        try {
            redirectStrategy.sendRedirect(request, response, "/members/join");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printOAuth2Information(OAuth2UserRequest userRequest) {
        System.out.println("USER Request: " + userRequest);
        System.out.println("getAccessToken: " + userRequest.getAccessToken());
        System.out.println("getAdditionalParameters: " + userRequest.getAdditionalParameters());
        System.out.println("getClientRegistration: " + userRequest.getClientRegistration()); // 어떤 Oauth로 로그인 했는지 확인
    }
}