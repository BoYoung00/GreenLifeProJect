package com.example.greenlifeproject.controller;

import com.example.greenlifeproject.config.auth.AuthenticationService;
import com.example.greenlifeproject.dto.BoardDTO;
import com.example.greenlifeproject.entity.MemberEntity;
import com.example.greenlifeproject.service.BoardService;
import com.example.greenlifeproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final AuthenticationService authenticationService;

    private final MemberService memberService;

    private final BoardService boardService;
    @GetMapping("/write")
    public String showWritePostPage(){

        return "board/writePost";
    }
    @PostMapping("/write")
    public String writeNewPost(BoardDTO boardDTO, Authentication authentication) throws IOException {
        String email = authenticationService.getCurrentUserEmail(authentication);
        MemberEntity member = memberService.findMemberEntityByEmail(email);

        boardDTO.setMember(member);
        for (int i=0; i<80; i++){
            boardService.saveNewBoardPost(boardDTO);
        }
        return "board/writePost";
    }

    @GetMapping("/posts")
    public String showPostsListPage(Model model,@PageableDefault(page = 0,size = 5,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){

        model.addAttribute("boardDTOList", boardService.showBoardPostList(pageable));

        return "board/posts";
    }
}
