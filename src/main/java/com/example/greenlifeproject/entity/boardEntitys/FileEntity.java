package com.example.greenlifeproject.entity.boardEntitys;

import com.example.greenlifeproject.entity.boardEntitys.BoardEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name = "BOARD_FILE")
@ToString
public class FileEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String originalFileName;
    //파일의 원본 명 ex) oneFile.txt
    @Column
    private String storedFileName;
    //실질적으로 저장될 파일의 이름  ex) c33151c9-3ae9-4d21_oneFile.txt

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private BoardEntity board;

    //Board Entity 와 BoardFileEntity 관계를 맺음
    //게시글과 파일 기준에서는 N -> 1이 댐
    //FetchType FetchType.LAZY 부모 자식 관계에서 부모랑 자식 관계에서 필요할때만 가져오기
    //JoinColum 은 테이블에 만들어지는 컬럼 이름을 말해줌! 특정 부모 Entity 가져오기

}
