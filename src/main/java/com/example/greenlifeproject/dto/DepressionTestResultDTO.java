package com.example.greenlifeproject.dto;

import com.example.greenlifeproject.entity.DepressionTestResults.DepressionTestResultEntity;
import com.example.greenlifeproject.entity.MemberEntity;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class  DepressionTestResultDTO{

    private Long id;

    private MemberEntity member;

    private LocalDateTime date;

    private Double score;

    private String interpretation;

    public static DepressionTestResultEntity convertToDepressionTestResultEntityEntity(DepressionTestResultDTO depressionTestResultDTO){

        DepressionTestResultEntity depressionTestResultEntity = new DepressionTestResultEntity();

        depressionTestResultEntity.setDate(LocalDateTime.now());

        depressionTestResultEntity.setScore(depressionTestResultDTO.getScore());

        depressionTestResultEntity.setMember(depressionTestResultDTO.getMember());

        depressionTestResultEntity.setInterpretation(depressionTestResultDTO.getInterpretation());

        return depressionTestResultEntity;
    }
}
