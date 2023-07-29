package com.example.greenlifeproject.repository;

import com.example.greenlifeproject.entity.chatEntitys.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatEntity,Long> {

}
