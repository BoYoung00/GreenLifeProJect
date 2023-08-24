package com.example.greenlifeproject.controller;

import com.example.greenlifeproject.config.auth.AuthenticationService;
import com.example.greenlifeproject.dto.DepressionTestResultDTO;
import com.example.greenlifeproject.entity.MemberEntity;
import com.example.greenlifeproject.service.DepressionService;
import com.example.greenlifeproject.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.PersistenceUnit;
import java.util.List;

@Controller
@RequestMapping("/survey")
@RequiredArgsConstructor
public class DepressionController {

    private final AuthenticationService authenticationService;

    private final DepressionService depressionService;

    private final MemberService memberService;

    @GetMapping("/depression")
    public String showDepressionTestPage(Authentication authentication, Model model){
        String name = memberService.getLoggedInUserName(authentication);

        model.addAttribute("username",name);
        //이름 추출하여 화면에 출력
        
        //화면값 배열 출력
        String questionFilePath = "C:\\SpringBoot\\GreenLifeProJect\\src\\main\\resources\\static\\file\\depression\\questions.txt";
        String resultFilePath = "C:\\SpringBoot\\GreenLifeProJect\\src\\main\\resources\\static\\file\\depression\\resultMessage";

        List<String> questionFileList = depressionService.loadQuestionsFromFile(questionFilePath);
        List<String> resultMessageList = depressionService.loadQuestionsFromFile(resultFilePath);

        model.addAttribute("questionFileList",questionFileList);
        model.addAttribute("resultFileList",resultMessageList);

        return "depression/depression-test";
    }
    @PostMapping("/depression")
    public String saveTestResultController(Authentication authentication, DepressionTestResultDTO depressionTestResultDTO){
        String email = authenticationService.getCurrentUserEmail(authentication);

        MemberEntity member = memberService.findMemberEntityByEmail(email);
        depressionTestResultDTO.setMember(member);

        depressionService.saveDepressionTestResult(depressionTestResultDTO);

        return "depression/depression-test";
    }

    @GetMapping("/depression-test-results")
    public String showDepressionResultPage(Model model,Authentication authentication) throws JsonProcessingException {
        String email = authenticationService.getCurrentUserEmail(authentication);

        MemberEntity member = memberService.findMemberEntityByEmail(email);
        Long memberID = member.getId();

        List<DepressionTestResultDTO> depressionTestResultDTOS = depressionService.getResultsForMemberOrderByDateDesc(memberID);

        model.addAttribute("depressionTestResultDTOS",depressionTestResultDTOS);
        return "depression/my-depression-history";
    }
}
