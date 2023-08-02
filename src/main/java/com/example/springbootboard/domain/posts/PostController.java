package com.example.springbootboard.domain.posts;

import com.example.springbootboard.common.CommonResponse;
import com.example.springbootboard.domain.posts.dto.PostRequestDTO;
import com.example.springbootboard.domain.posts.dto.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
@Controller
public class PostController {
    private final PostService postService;

    @PostMapping("/write")
    public String postWritePostPage(Model model, PostRequestDTO postRequestDTO, List<MultipartFile> multipartFiles) {
        // TODO: User 기능 추가 후 제거
        postRequestDTO.setUserId(1l);

        PostResponseDTO postResponseDTO = postService.insertPost(postRequestDTO, multipartFiles);

        model.addAttribute(postResponseDTO);

        return "redirect:/boards/detail?teamName=" + postRequestDTO.getTeamName() + "&postId=" + postResponseDTO.getPostId();
    }

    @PostMapping("/write/api")
    public CommonResponse<Object> createPost(
            @Valid @RequestPart(value = "postRequestDTO") PostRequestDTO postRequestDTO,
            @RequestPart(value = "multipartFiles") List<MultipartFile> multipartFiles) {
        // TODO: User 기능 추가 후 제거
        postRequestDTO.setUserId(1L);

        return CommonResponse.success(postService.insertPost(postRequestDTO, multipartFiles));
    }


    @PostMapping("/update")
    public String postUpdatePostPage(Model model, PostRequestDTO postRequestDTO) {
        // TODO: 유저 기능 추가 후 삭제
        postRequestDTO.setUserId(1l);
        PostResponseDTO postResponseDTO = postService.updatePost(postRequestDTO);

        model.addAttribute(postResponseDTO);

        return "redirect:/boards/detail?teamName=" + postResponseDTO.getTeamName() + "&postId=" + postRequestDTO.getPostId();
    }

    @PutMapping("/update/api")
    public CommonResponse<Object> updatePost(@Valid @RequestBody PostRequestDTO postRequest) {
        // TODO: 유저 기능 추가 후 삭제
        postRequest.setUserId(1l);

        return CommonResponse.success(postService.updatePost(postRequest));
    }


    @PostMapping("/delete")
    public String deletePost(Model model, @RequestParam Long postId, @RequestParam String teamName) {
        postService.delete(postId);

        return "redirect:/boards/list?teamName=" + teamName + "&postId=" + postId + "&age=0&size=10&sort=postId,DESC";
    }
}
