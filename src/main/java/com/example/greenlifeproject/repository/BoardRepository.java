package com.example.greenlifeproject.repository;

import com.example.greenlifeproject.entity.boardEntitys.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
}
