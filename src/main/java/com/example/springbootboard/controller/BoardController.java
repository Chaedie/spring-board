package com.example.springbootboard.controller;

import com.example.springbootboard.data.dto.PostRequestDTO;
import com.example.springbootboard.data.dto.PostResponseDTO;
import com.example.springbootboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        return "boards/postList";
    }

    @GetMapping("/write")
    public String getWritePostPage(Model model) {
        return "boards/writePostPage";
    }

    @PostMapping("/write")
    public String postWritePostPage(Model model,
                                    @RequestParam String title,
                                    @RequestParam String content) {
        PostRequestDTO postRequestDTO = new PostRequestDTO(title, content, 1l);
        PostResponseDTO postResponseDTO = postService.insertPost(postRequestDTO);
        model.addAttribute(postResponseDTO);
        Long postId = postResponseDTO.getPostId();


        return "redirect:" + postId;
    }

    @GetMapping("/{postId}")
    public String getWritePostPage(Model model, @PathVariable Long postId) {
        PostResponseDTO postResponseDTO = postService.findById(postId);
        model.addAttribute(postResponseDTO);

        return "boards/detailPage";
    }


}
