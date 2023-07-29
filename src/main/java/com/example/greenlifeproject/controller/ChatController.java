package com.example.greenlifeproject.controller;

import com.example.greenlifeproject.dto.ChatDTO;
import com.example.greenlifeproject.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    @GetMapping("/room")
    public String showOpenChatPage(Authentication authentication){

        return "openChat/chatting";
    }

    @MessageMapping("/chat") // 이 주소로 들어오는 메시지를 처리합니다.
    @SendTo("/topic/messages") // 이 주소로 응답을 브로드캐스팅(재 응답) 합니다.
    public ChatDTO handleMessage(ChatDTO message) {

        System.out.println(message);

        return new ChatDTO();
    }
}
