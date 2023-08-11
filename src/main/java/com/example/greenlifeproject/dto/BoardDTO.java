package com.example.greenlifeproject.dto;


import com.example.greenlifeproject.entity.MemberEntity;
import com.example.greenlifeproject.entity.boardEntitys.BoardEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Getter
@Setter
@ToString
public class BoardDTO extends BaseDTO{

    private Long id;

    private String title;

    private String content;

    private MemberEntity member;

    private int boardHits;

    private int boardAvailable; //게시글 삭제 여부 0이면 미첨부 1이면 첨부

    private int isAttached; //파일 첨부여부 0이면 미첨부 1이면 첨부

    private MultipartFile boardFile; //파일 첨부 여부

    public static BoardDTO convertToBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setId(boardEntity.getId());
        boardDTO.setTitle(boardEntity.getTitle());
        boardDTO.setContent(boardEntity.getContent());
        boardDTO.setMember(boardEntity.getMember());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardAvailable(boardEntity.getBoardAvailable());
        boardDTO.setIsAttached(boardEntity.getIsAttached());

        boardDTO.setUpdatedTime(boardEntity.getUpdatedTime());
        boardDTO.setCreateDataTime(boardEntity.getCreateDataTime());

        return boardDTO;
    }

}
