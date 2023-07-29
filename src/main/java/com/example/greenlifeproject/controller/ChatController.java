package com.example.greenlifeproject.controller;

import com.example.greenlifeproject.config.auth.AuthenticationService;
import com.example.greenlifeproject.dto.ChatDTO;
import com.example.greenlifeproject.dto.MemberDTO;
import com.example.greenlifeproject.entity.MemberEntity;
import com.example.greenlifeproject.service.ChatService;
import com.example.greenlifeproject.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    private final AuthenticationService authenticationService;

    private final MemberService memberService;
    @GetMapping("/room")
    public String showOpenChatPage(Authentication authentication, Model model){
        String email = authenticationService.getCurrentUserEmail(authentication);
        String name = authenticationService.getCurrentUser(authentication);

        if (name.length() > 7){
            name = name.substring(name.length()-3);
            //이름 추출하기
        }

        System.out.println(email);

        model.addAttribute("name",name);
        model.addAttribute("userEmail",email);

        return "openChat/chatting";
    }

    @MessageMapping("/chat") // 이 주소로 들어오는 메시지를 처리
    @SendTo("/topic/messages") // 이 주소로 응답을 브로드캐스팅(재 응답)
    public ChatDTO handleMessage(ChatDTO chatDTO,Authentication authentication) {
        String email = authenticationService.getCurrentUserEmail(authentication);
        MemberEntity member = memberService.findMemberEntityByEmail(email);

        chatDTO.setMember(member);
        chatService.saveChatMessage(chatDTO);

        chatDTO.setChatTime(LocalDateTime.now());

        return chatDTO;
    }
}
