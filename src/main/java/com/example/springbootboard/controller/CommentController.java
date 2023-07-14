package com.example.springbootboard.controller;

import com.example.springbootboard.data.dto.CommentDTO;
import com.example.springbootboard.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/boards")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/detail/comments")
    public String writeComment(@RequestParam Long postId, @RequestParam String teamName, CommentDTO.Request commentRequestDTO) {
        commentService.insertComment(commentRequestDTO);

        return "redirect:/boards/detail?teamName=" + teamName + "&postId=" + postId;
    }
}
