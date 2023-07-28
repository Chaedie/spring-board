package com.example.springbootboard.domain.comments;

import com.example.springbootboard.domain.comments.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller("/boards")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/detail/comments")
    public String writeComment(@RequestParam Long postId, @RequestParam String teamName, CommentDTO.Request commentRequestDTO) {
        commentService.insertComment(commentRequestDTO);

        return "redirect:/boards/detail?teamName=" + teamName + "&postId=" + postId;
    }
}
