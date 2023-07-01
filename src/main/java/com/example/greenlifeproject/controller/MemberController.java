package com.example.greenlifeproject.controller;

import com.example.greenlifeproject.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    @GetMapping("/join")
    public String joinForm(){

        return "/members/join";
    }
    @PostMapping("/joinProc")
    public String joinProc(MemberDTO memberDTO){
        System.out.println(memberDTO);
        return "/members/join";
    }
}
