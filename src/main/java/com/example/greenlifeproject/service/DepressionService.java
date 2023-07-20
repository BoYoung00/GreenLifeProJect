package com.example.greenlifeproject.service;

import com.example.greenlifeproject.dto.DepressionTestResultDTO;

import com.example.greenlifeproject.entity.DepressionTestResults.DepressionTestResultEntity;
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
        DepressionTestResultEntity depressionTestResultEntity = DepressionTestResultDTO.convertToDepressionTestResultEntityEntity(depressionTestResultDTO);
        System.out.println("저장실행");
        depressionTestResultRepository.save(depressionTestResultEntity);
    }
}
