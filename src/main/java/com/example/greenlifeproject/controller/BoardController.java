package com.example.greenlifeproject.controller;

import com.example.greenlifeproject.config.auth.AuthenticationService;
import com.example.greenlifeproject.dto.BoardCommentDTO;
import com.example.greenlifeproject.dto.BoardDTO;
import com.example.greenlifeproject.dto.MemberDTO;
import com.example.greenlifeproject.entity.MemberEntity;
import com.example.greenlifeproject.entity.boardEntitys.BoardEntity;
import com.example.greenlifeproject.service.BoardCommentService;
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
import org.springframework.web.bind.annotation.PathVariable;
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

    private final BoardCommentService boardCommentService;
    @GetMapping("/write")
    public String showWritePostPage(){

        return "board/writePost";
    }
    @PostMapping("/write")
    public String writeNewPost(BoardDTO boardDTO, Authentication authentication) throws IOException {
        String email = authenticationService.getCurrentUserEmail(authentication);
        MemberEntity member = memberService.findMemberEntityByEmail(email);

        boardDTO.setMember(member);

        boardService.saveNewBoardPost(boardDTO);

        return "board/posts";
    }

    @GetMapping("/posts")
    public String showPostsListPage(Model model,@PageableDefault(page = 0,size = 5,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){

        model.addAttribute("boardDTOList", boardService.showBoardPostList(pageable));

        return "board/posts";
    }

    @GetMapping("/detailPost/{id}")
    public String showDetailPostPage(@PathVariable Long id,Model model,Authentication authentication){
        //이름 추출하여 화면에 출력
        String name = memberService.getLoggedInUserName(authentication);
        model.addAttribute("username",name);

        //조회수 증가
        boardService.updateHits(id);

        //해당 게시글 찾아서 보여주기
        BoardDTO boardDTO = boardService.findBoardDTOById(id);
        model.addAttribute("boardDTO",boardDTO);

        //조회수 조회해서 보여주기
        int userPostHits = boardService.findByPostHits(id);
        model.addAttribute("userPostHits",userPostHits);

        List<BoardCommentDTO> commentDTOS = boardCommentService.findByCommentList(id);

        for (int i=0; i<commentDTOS.size(); i++){
            System.out.println(commentDTOS);
        }
        return "board/detail";
    }

    @GetMapping("/posts/user")
    public String showUserPostsPage(Authentication authentication,Model model){
        String name = memberService.getLoggedInUserName(authentication);
        model.addAttribute("username",name);
        //user 이름

        String email = authenticationService.getCurrentUserEmail(authentication);
        MemberEntity memberEntity = memberService.findMemberEntityByEmail(email);
        int userPostCount = boardService.getUserPostCount(memberEntity);
        model.addAttribute("count",userPostCount);
        //userPost 개수

        return "userPost";
    }

    @PostMapping("/comment")
    public String writePostComment(Long id,String content,Authentication authentication){
        BoardCommentDTO boardCommentDTO = new BoardCommentDTO();
        BoardEntity board = boardService.findBoardEntityById(id);

        boardCommentDTO.setBoard(board);
        boardCommentDTO.setContent(content);

        String email = authenticationService.getCurrentUserEmail(authentication);
        MemberEntity member = memberService.findMemberEntityByEmail(email);
        boardCommentDTO.setWriter(member);

        boardCommentService.saveNewComment(boardCommentDTO);

        return "redirect:/board/detailPost/"+id;
    }
}
