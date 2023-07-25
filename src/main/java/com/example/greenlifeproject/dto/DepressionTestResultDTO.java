package com.example.greenlifeproject.dto;

import com.example.greenlifeproject.entity.DepressionTestResults.DepressionTestResultEntity;
import com.example.greenlifeproject.entity.MemberEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class  DepressionTestResultDTO{

    private Long id;

    private MemberEntity member;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;

    private Double score;

    private String interpretation;

    private String level;

    public static DepressionTestResultDTO convertToDepressionTestResultDTO(DepressionTestResultEntity depressionTestResultEntity){
        DepressionTestResultDTO depressionTestResultDTO = new DepressionTestResultDTO();

        depressionTestResultDTO.setId(depressionTestResultEntity.getId());

        depressionTestResultDTO.setMember(depressionTestResultEntity.getMember());

        depressionTestResultDTO.setDate(depressionTestResultEntity.getDate());

        depressionTestResultDTO.setScore(depressionTestResultEntity.getScore());

        depressionTestResultDTO.setInterpretation(depressionTestResultEntity.getInterpretation());

        depressionTestResultDTO.setLevel(depressionTestResultEntity.getLevel());

        return depressionTestResultDTO;
    }
}
