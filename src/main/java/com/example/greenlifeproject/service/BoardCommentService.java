package com.example.greenlifeproject.service;

import com.example.greenlifeproject.dto.BoardCommentDTO;
import com.example.greenlifeproject.entity.boardEntitys.BoardEntity;
import com.example.greenlifeproject.entity.boardEntitys.Comment;
import com.example.greenlifeproject.repository.BoardRepository;
import com.example.greenlifeproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardCommentService {

    private final CommentRepository commentRepository;

    private final BoardRepository boardRepository;
    public void saveNewComment(BoardCommentDTO commentDTO){
        Comment comment = Comment.convertToCommentEntity(commentDTO);

        commentRepository.save(comment);
    }

    @Transactional
    public List<BoardCommentDTO> findByCommentList(Long id) {
        List<BoardCommentDTO> commentDTOS = new ArrayList<>();

        Optional<BoardEntity> boardEntity = boardRepository.findById(id);

        if (boardEntity.isPresent()){
            List<Comment> comments = commentRepository.findAllByBoardOrderById(boardEntity.get());

            for (int i=0; i<comments.size(); i++){
                commentDTOS.add(BoardCommentDTO.convertToCommentDTO(comments.get(i)));
            }

            return commentDTOS;
        }
        throw new NoSuchElementException("Board with ID " + id + " not found");
    }
}
