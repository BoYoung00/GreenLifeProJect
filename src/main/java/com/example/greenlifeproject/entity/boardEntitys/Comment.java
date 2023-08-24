package com.example.greenlifeproject.entity.boardEntitys;

import com.example.greenlifeproject.dto.BoardCommentDTO;
import com.example.greenlifeproject.entity.MemberEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "COMMENT")
@Data
@ToString
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private BoardEntity board;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity member;

    public static Comment convertToCommentEntity(BoardCommentDTO commentDTO) {
        Comment comment = new Comment();

        comment.setMember(commentDTO.getWriter());
        comment.setContent(commentDTO.getContent());
        comment.setBoard(commentDTO.getBoard());
        return comment;
    }
}
