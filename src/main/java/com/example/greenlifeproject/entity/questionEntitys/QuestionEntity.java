package com.example.greenlifeproject.entity.questionEntitys;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "QUESTION")
@Data
@ToString
public class QuestionEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "MEM_ID")
    private Integer memberId;

    @Column(name = "Q_DATE")
    private LocalDateTime date;

    @Column(name = "Q_SCORE")
    private Double score;

    @Column(name = "Q_ANS")
    private String interpretation;
}
