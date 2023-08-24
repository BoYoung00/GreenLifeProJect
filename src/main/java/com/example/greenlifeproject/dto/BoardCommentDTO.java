package com.example.greenlifeproject.dto;

import com.example.greenlifeproject.entity.MemberEntity;
import com.example.greenlifeproject.entity.boardEntitys.BoardEntity;
import com.example.greenlifeproject.entity.boardEntitys.Comment;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Data
public class BoardCommentDTO {

    private BoardEntity board;

    private String content;

    private MemberEntity writer;

    public static BoardCommentDTO convertToCommentDTO(Comment comment){
        BoardCommentDTO boardCommentDTO = new BoardCommentDTO();

        boardCommentDTO.setBoard(comment.getBoard());
        boardCommentDTO.setContent(comment.getContent());
        boardCommentDTO.setWriter(comment.getMember());

        return boardCommentDTO;
    }
}
