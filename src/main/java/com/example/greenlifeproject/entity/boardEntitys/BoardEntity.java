package com.example.greenlifeproject.entity.boardEntitys;

import com.example.greenlifeproject.dto.BoardDTO;
import com.example.greenlifeproject.entity.BaseEntity;
import com.example.greenlifeproject.entity.MemberEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOARD")
@Data
@ToString
public class BoardEntity extends BaseEntity {
    
    @Id
    @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity member;

    @Column(nullable = false)
    private int boardAvailable; //게시글 삭제 여부

    private int isAttached; //파일 첨부 여부

    private int boardHits;

    @OneToMany(mappedBy = "board",  cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.EAGER)
    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    private List<FileEntity> boardFileEntityList=new ArrayList<>();

    public static BoardEntity convertToBoardEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setMember(boardDTO.getMember());

        boardEntity.setBoardHits(boardDTO.getBoardHits());

        boardEntity.setBoardAvailable(0);
        boardEntity.setIsAttached(0);

        boardEntity.setCreateDataTime(boardDTO.getCreateDataTime());
        boardEntity.setUpdatedTime(boardDTO.getUpdatedTime());

        return boardEntity;
    }
    public static BoardEntity convertFileToBoardEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setMember(boardDTO.getMember());

        boardEntity.setBoardHits(boardDTO.getBoardHits());

        boardEntity.setBoardAvailable(0);
        boardEntity.setIsAttached(1);

        boardEntity.setCreateDataTime(boardDTO.getCreateDataTime());
        boardEntity.setUpdatedTime(boardDTO.getUpdatedTime());

        return boardEntity;
    }

}
