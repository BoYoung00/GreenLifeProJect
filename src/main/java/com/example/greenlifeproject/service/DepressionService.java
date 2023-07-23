package com.example.greenlifeproject.service;

import com.example.greenlifeproject.dto.DepressionTestResultDTO;

import com.example.greenlifeproject.entity.DepressionTestResults.DepressionTestResultEntity;
import com.example.greenlifeproject.entity.MemberEntity;
import com.example.greenlifeproject.repository.DepressionTestResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepressionService {

    private final DepressionTestResultRepository depressionTestResultRepository;

    private final MemberService memberService;
    public List<String> loadQuestionsFromFile(String filePath) {
        List<String> questionList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                questionList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questionList;
    }

    public void saveDepressionTestResult(DepressionTestResultDTO depressionTestResultDTO){
        DepressionTestResultEntity depressionTestResultEntity = DepressionTestResultEntity.convertToDepressionTestResultEntity(depressionTestResultDTO);
        System.out.println("저장실행");
        depressionTestResultRepository.save(depressionTestResultEntity);
    }

    public List<DepressionTestResultDTO> getResultsForMemberOrderByDateDesc(Long memberId){
        List<DepressionTestResultDTO> depressionTestResultDTOS = new ArrayList<>();
        MemberEntity member = memberService.findMemberEntityByID(memberId);

        if (member != null){
            List<DepressionTestResultEntity> depressionTestResultEntities = depressionTestResultRepository.findAllByMemberOrderByDateDesc(member);

            for (DepressionTestResultEntity  depressionTestResult: depressionTestResultEntities){
                depressionTestResultDTOS.add(DepressionTestResultDTO.convertToDepressionTestResultDTO(depressionTestResult));
            }
        }

        return depressionTestResultDTOS;
    }
}
