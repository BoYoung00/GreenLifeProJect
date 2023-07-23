package com.example.greenlifeproject.entity.DepressionTestResults;

import com.example.greenlifeproject.dto.DepressionTestResultDTO;
import com.example.greenlifeproject.entity.MemberEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DepressionTestResult")
@Data
@ToString
public class DepressionTestResultEntity {
    @Id
    @GeneratedValue
    @Column(name = "QUESTION_ID")
    private Long id;

    // 질문들

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity member;

    // 검사 날짜
    @Column(name = "Q_DATE")
    private LocalDateTime date;

    // 점수
    @Column(name = "Q_SCORE")
    private Double score;

    // 결과값
    @Column(name = "Q_ANS")
    private String interpretation;

    @Column(name = "level")
    private String level;

    public static DepressionTestResultEntity convertToDepressionTestResultEntity(DepressionTestResultDTO depressionTestResultDTO){

        DepressionTestResultEntity depressionTestResultEntity = new DepressionTestResultEntity();

        depressionTestResultEntity.setDate(LocalDateTime.now());

        depressionTestResultEntity.setScore(depressionTestResultDTO.getScore());

        depressionTestResultEntity.setMember(depressionTestResultDTO.getMember());

        depressionTestResultEntity.setInterpretation(depressionTestResultDTO.getInterpretation());

        depressionTestResultEntity.setLevel(depressionTestResultDTO.getLevel());

        return depressionTestResultEntity;
    }
}
