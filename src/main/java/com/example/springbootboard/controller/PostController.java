package com.example.springbootboard.controller;

import com.example.springbootboard.data.dto.PostRequestDTO;
import com.example.springbootboard.data.dto.PostResponseDTO;
import com.example.springbootboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/boards")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/list")
    public String getPostList(Model model, @RequestParam String teamName, @RequestParam(required = false, defaultValue = "") String search, Pageable pageable) {
        Page<PostResponseDTO> postResponseDTOList = null;
        try {
            if (teamName.equals("all")) {
                postResponseDTOList = postService.findAllWithPagination(search, pageable);
            } else {
                postResponseDTOList = postService.findAllByTeamNameWithPagination(teamName, search, pageable);
            }
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            System.out.println("BoardController 에서 에러발생, 메인으로 리다이렉트");
            return "redirect:/";
        }

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

        return "redirect:detail?teamName=" + postRequestDTO.getTeamName() + "&postId=" + postResponseDTO.getPostId();
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
            return "redirect:detail?teamName=" + postRequestDTO.getTeamName() + "&postId=" + postRequestDTO.getPostId();
        }
        model.addAttribute(postResponseDTO);

        return "redirect:detail?teamName=" + postRequestDTO.getTeamName() + "&postId=" + postRequestDTO.getPostId();
    }

    @GetMapping("/detail")
    public String getWritePostPage(Model model, @RequestParam Long postId) {
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
    public String getDeletePage(Model model, @RequestParam Long postId, @RequestParam String teamName) {
        model.addAttribute("postId", postId);
        model.addAttribute("teamName", teamName);

        return "boards/deletePage";
    }

    @PostMapping("/delete")
    public String deletePost(Model model, @RequestParam Long postId, @RequestParam String teamName) {
        try {
            postService.delete(postId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:list?teamName=" + teamName + "&postId=" + postId + "&age=0&size=10&sort=postId,DESC";
    }
}
