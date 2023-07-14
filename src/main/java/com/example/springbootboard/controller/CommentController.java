package com.example.springbootboard.controller;

import com.example.springbootboard.data.dto.CommentDTO;
import com.example.springbootboard.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public String writeComment(@PathVariable Long postId, CommentDTO.Request commentRequestDTO) {
        commentService.insertComment(commentRequestDTO);

        return "redirect:/boards/" + postId;
    }
}
