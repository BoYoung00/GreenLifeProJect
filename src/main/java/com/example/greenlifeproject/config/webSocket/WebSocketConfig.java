package com.example.greenlifeproject.config.webSocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); //보내는 부분을 /topic 경로로 설정
        registry.setApplicationDestinationPrefixes("/app"); //받는 부분을 /app 으로 설정
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat") //소켓 서버 연결 주소  const socket = new SockJS("http://localhost:8080/chat"); 뒤에부분이랑 맞춰주기
                .setAllowedOriginPatterns("*") //브라우저의 모든 연결을 허용 시켜주고
                .withSockJS();
    }
}
