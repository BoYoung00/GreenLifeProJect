package com.example.greenlifeproject.repository;

import com.example.greenlifeproject.dto.BoardCommentDTO;
import com.example.greenlifeproject.dto.BoardDTO;
import com.example.greenlifeproject.entity.boardEntitys.BoardEntity;
import com.example.greenlifeproject.entity.boardEntitys.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByBoardOrderById(BoardEntity board);
}
