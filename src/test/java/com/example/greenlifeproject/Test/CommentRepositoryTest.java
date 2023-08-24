package com.example.greenlifeproject.Test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import com.example.greenlifeproject.repository.BoardRepository;
import com.example.greenlifeproject.repository.CommentRepository;
import com.example.greenlifeproject.entity.boardEntitys.BoardEntity;
import com.example.greenlifeproject.entity.boardEntitys.Comment;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CommentRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void testFindCommentsByBoard() {
        // Create a sample board entity and save it
        BoardEntity boardEntity = new BoardEntity();
        // Set boardEntity's fields
        boardRepository.save(boardEntity);

        // Create a sample comment and associate it with the board
        Comment comment = new Comment();
        comment.setBoard(boardEntity);
        comment.setContent("테스트용");
        // Set other comment fields
        commentRepository.save(comment);

        // Fetch the comments associated with the board
        List<Comment> comments = commentRepository.findAllByBoardOrderById(boardEntity);

        // Perform assertions or print statements to verify the result
        System.out.println("Comments size: " + comments.size());
        for (Comment c : comments) {
            System.out.println("Comment ID: " + c.getId());
            // Add more assertions or checks as needed
        }
    }
}
