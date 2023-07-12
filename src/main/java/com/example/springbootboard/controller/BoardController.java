package com.example.springbootboard.controller;

import com.example.springbootboard.data.dto.PostRequestDTO;
import com.example.springbootboard.data.dto.PostResponseDTO;
import com.example.springbootboard.service.Impl.AwsS3ServiceImpl;
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
    private final AwsS3ServiceImpl awsS3Service;

    @Autowired
    public BoardController(PostService postService, AwsS3ServiceImpl awsS3Service) {
        this.postService = postService;
        this.awsS3Service = awsS3Service;
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
    public String postWritePostPage(Model model, PostRequestDTO postRequestDTO, MultipartFile multipartFile) {
        // TODO: User 기능 추가 후 제거
        postRequestDTO.setUserId(1l);
        postRequestDTO.setFileUrl(awsS3Service.upload(multipartFile));
        // postRequestDTO.setFileUrlList(awsS3Service.upload(multipartFile));

        System.out.println("postRequestDTO = " + postRequestDTO);
        PostResponseDTO postResponseDTO = postService.insertPost(postRequestDTO);
        model.addAttribute(postResponseDTO);
        Long postId = postResponseDTO.getPostId();

        return "redirect:" + postId;
    }

    // @PostMapping("/write")
    // public String postWritePostPage(Model model, PostRequestDTO postRequestDTO, MultipartFile[] multipartFiles) {
    //     // TODO: User 기능 추가 후 제거
    //     postRequestDTO.setUserId(1l);
    //     postRequestDTO.setFileUrlList(awsS3Service.upload(multipartFiles));
    //
    //     System.out.println("postRequestDTO = " + postRequestDTO);
    //     PostResponseDTO postResponseDTO = postService.insertPost(postRequestDTO);
    //     model.addAttribute(postResponseDTO);
    //     Long postId = postResponseDTO.getPostId();
    //
    //     return "redirect:" + postId;
    // }


    @GetMapping("/update")
    public String getUpdatePostPage(Model model, @RequestParam Long postId) {
        PostResponseDTO postResponseDTO = postService.findById(postId);
        model.addAttribute(postResponseDTO);

        return "boards/updatePostPage";
    }

    @PostMapping("/update")
    public String postUpdatePostPage(Model model, PostRequestDTO postRequestDTO) throws EntityNotFoundException {
        // TODO: 유저 기능 추가 후 삭제
        postRequestDTO.setUserId(1l);
        PostResponseDTO postResponseDTO = null;
        try {
            postResponseDTO = postService.updatePost(postRequestDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute(postResponseDTO);

        return "redirect:" + postResponseDTO.getPostId();
    }

    @GetMapping("/{postId}")
    public String getWritePostPage(Model model, @PathVariable Long postId) {
        PostResponseDTO postResponseDTO = null;
        try {
            postResponseDTO = postService.findById(postId);
            System.out.println("postResponseDTO = " + postResponseDTO);
            model.addAttribute(postResponseDTO);
            return "boards/detailPage";
        } catch (EntityNotFoundException e) {
        }
        return "errors/notFound";
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
