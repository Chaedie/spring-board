package com.example.springbootboard.controller;

import com.example.springbootboard.data.dto.PostRequestDTO;
import com.example.springbootboard.data.dto.PostResponseDTO;
import com.example.springbootboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/boards")
public class BoardController {
    private final PostService postService;

    @Autowired
    public BoardController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/list")
    public String getPostList(Model model, @RequestParam(required = false, defaultValue = "") String search, Pageable pageable) {
        Page<PostResponseDTO> postResponseDTOList = postService.findAllWithPagination(search, pageable);

        model.addAttribute("listName", "Post");
        model.addAttribute("responseDTOList", postResponseDTOList.toList());
        model.addAttribute("totalCount", postResponseDTOList.getTotalElements());

        return "boards/postList";
    }

    @GetMapping("/write")
    public String getWritePostPage(Model model) {
        return "boards/writePostPage";
    }

    @PostMapping("/write")
    public String postWritePostPage(Model model, PostRequestDTO postRequestDTO, MultipartFile[] multipartFiles) {
        // TODO: User 기능 추가 후 제거
        postRequestDTO.setUserId(1l);

        PostResponseDTO postResponseDTO = postService.insertPost(postRequestDTO, multipartFiles);

        model.addAttribute(postResponseDTO);

        return "redirect:" + postResponseDTO.getPostId();
    }

    @GetMapping("/update")
    public String getUpdatePostPage(Model model, @RequestParam Long postId) {
        PostResponseDTO postResponseDTO = postService.findById(postId);
        model.addAttribute(postResponseDTO);

        return "boards/updatePostPage";
    }

    @PostMapping("/update")
    public String postUpdatePostPage(Model model, PostRequestDTO postRequestDTO) {
        // TODO: 유저 기능 추가 후 삭제
        postRequestDTO.setUserId(1l);
        PostResponseDTO postResponseDTO = null;
        try {
            postResponseDTO = postService.updatePost(postRequestDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:" + postRequestDTO.getPostId();
        }
        model.addAttribute(postResponseDTO);

        return "redirect:" + postRequestDTO.getPostId();
    }

    @GetMapping("/{postId}")
    public String getWritePostPage(Model model, @PathVariable Long postId) {
        PostResponseDTO postResponseDTO = null;
        try {
            postResponseDTO = postService.findById(postId);
            model.addAttribute(postResponseDTO);
            return "boards/detailPage";
        } catch (EntityNotFoundException e) {
        }
        return "errors/notFound";
    }

    @GetMapping("/delete")
    public String getDeletePage(Model model, @RequestParam Long postId) {
        model.addAttribute("postId", postId);
        return "boards/deletePage";
    }

    @PostMapping("/delete")
    public String deletePost(Model model, @RequestParam Long postId) {
        try {
            postService.delete(postId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:list?page=0&size=10&sort=postId,DESC";
    }
}
