package com.example.springbootboard.controller;

import com.example.springbootboard.data.dto.PostResponseDTO;
import com.example.springbootboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {
    private final PostService postService;

    @Autowired
    public BoardController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/list")
    public String getPostList(Model model) {
        List<PostResponseDTO> postResponseDTOList = postService.findAll();

        model.addAttribute("listName", "Post");
        model.addAttribute("responseDTOList", postResponseDTOList);

        return "postList";
    }
}
