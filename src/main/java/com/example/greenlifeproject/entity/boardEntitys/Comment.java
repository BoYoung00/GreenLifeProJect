package com.example.greenlifeproject.entity.boardEntitys;

import com.example.greenlifeproject.entity.boardEntitys.BoardEntity;
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
    private Long comment_id;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private BoardEntity board;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "AVAILABLE")
    private String commentAvailable;
    //댓글 삭제 여부
}
