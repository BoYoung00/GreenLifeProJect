package com.example.greenlifeproject.entity.boardEntitys;

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

    @OneToMany(mappedBy = "board",  cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    private List<FileEntity> boardFileEntityList=new ArrayList<>();

}
