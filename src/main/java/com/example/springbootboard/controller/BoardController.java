package com.example.springbootboard.controller;

import com.example.springbootboard.data.dto.PostRequestDTO;
import com.example.springbootboard.data.dto.PostResponseDTO;
import com.example.springbootboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
        PostRequestDTO postRequestDTO = new PostRequestDTO().builder()
                .postTitle(title)
                .postContent(content)
                .userId(1l).build();
        PostResponseDTO postResponseDTO = postService.insertPost(postRequestDTO);
        model.addAttribute(postResponseDTO);
        Long postId = postResponseDTO.getPostId();

        return "redirect:" + postId;
    }

    @GetMapping("/update")
    public String getUpdatePostPage(Model model, @RequestParam Long postId) {
        PostResponseDTO postResponseDTO = postService.findById(postId);
        model.addAttribute(postResponseDTO);

        return "boards/updatePostPage";
    }

    @PostMapping("/update")
    public String postUpdatePostPage(Model model,
                                     @RequestParam String title,
                                     @RequestParam String content,
                                     @RequestParam String postId) throws EntityNotFoundException {
        PostRequestDTO postRequestDTO = new PostRequestDTO().builder()
                .postId(Long.parseLong(postId))
                .postTitle(title)
                .postContent(content)
                .userId(1l).build();
        PostResponseDTO postResponseDTO = null;
        try {
            postResponseDTO = postService.updatePost(postRequestDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute(postResponseDTO);


        return "redirect:" + postId;
    }

    @GetMapping("/{postId}")
    public String getWritePostPage(Model model, @PathVariable Long postId) {
        PostResponseDTO postResponseDTO = postService.findById(postId);
        model.addAttribute(postResponseDTO);

        return "boards/detailPage";
    }


}
