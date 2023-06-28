package com.example.greenlifeproject.controller;

import com.example.greenlifeproject.dto.MemberDTO;
import com.example.greenlifeproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/loginForm")
    public String loginForm(){

        return "member/loginForm";
    }
    @GetMapping("/joinForm")
    public String joinForm(){

        return "member/joinForm";
    }

    @PostMapping("/joinAction")
    public String joinAction(MemberDTO member, Model model) {
        String email = member.getEmail();
        boolean isExist = memberService.existsByEmail(email);

        if (isExist) {
            model.addAttribute("message", "중복된 이메일이 있습니다");
            model.addAttribute("searchUrl", "http://localhost:8080/joinForm");
            model.addAttribute("member", member); // 입력한 값 유지를 위해 member 객체 추가
            return "member/errorMsg";
        }
        else {
            memberService.addNewMember(member);
            return "redirect:http://localhost:8080/loginForm";
        }
    }

}
