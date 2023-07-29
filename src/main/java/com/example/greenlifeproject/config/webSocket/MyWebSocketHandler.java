package com.example.greenlifeproject.config.webSocket;

import com.example.greenlifeproject.dto.ChatDTO;
import com.example.greenlifeproject.service.ChatService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage) throws IOException {
        String payload = textMessage.getPayload();
        // 클라이언트로부터 받은 메시지
        // JSON 형식의 응답 메시지 생성
        JSONObject responseJson = new JSONObject();

        System.out.println(payload);

        responseJson.put("message", "서버에서의 응답: " + payload);

        webSocketSession.sendMessage(new TextMessage(responseJson.toString()));
    }
}
