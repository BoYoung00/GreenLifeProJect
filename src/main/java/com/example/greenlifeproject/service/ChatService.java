package com.example.greenlifeproject.service;

import com.example.greenlifeproject.dto.ChatDTO;
import com.example.greenlifeproject.entity.chatEntitys.ChatEntity;
import com.example.greenlifeproject.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private ChatRepository chatRepository;

    public void saveChatMessage(ChatDTO chatDTO){
        ChatEntity chatEntity = ChatEntity.convertToChatEntity(chatDTO);

        chatRepository.save(chatEntity);
    }
}
