package com.example.greenlifeproject.controller;

import com.example.greenlifeproject.dto.MemberDTO;
import com.example.greenlifeproject.entity.MemberEntity;
import com.example.greenlifeproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    @GetMapping("/join")
    public String showJoinPage(Model model, HttpSession httpSession){
        MemberDTO memberDTO = (MemberDTO) httpSession.getAttribute("memberDTO");

        System.out.println(memberDTO);

        if (memberDTO != null)
            model.addAttribute("memberDTO",memberDTO);

        return "/member/join";
    }

    @PostMapping("/joinProc")
    public String handleJoinRequest(@Valid MemberDTO memberDTO,
                                    BindingResult bindingResult , Model model,HttpSession httpSession){
        if (bindingResult.hasErrors()){
            return "member/join";
        }
        try {
            MemberEntity member = memberService.saveMember(memberDTO);
            httpSession.removeAttribute("memberDTO");
            //memberDTO 라는 이름에 세션 뺏어주기
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "/member/join";
        }
        return "redirect:/";

    }

    @GetMapping("/login")
    public String showLoginPage(){

        return "/member/login";
    }

    @GetMapping("/loginError")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception, Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "/member/login";
    }

}
