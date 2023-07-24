package com.example.springbootboard.service.impl;

import com.example.springbootboard.data.dto.PostRequestDTO;
import com.example.springbootboard.data.dto.PostResponseDTO;
import com.example.springbootboard.data.repository.PostRepository;
import com.example.springbootboard.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PostServiceImplTest {

    // TEST CODE 는 filed Injection 해도 상관없다.
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Test
    void insertPost() {
        // given
        PostRequestDTO postRequest = PostRequestDTO.builder()
                .postTitle("글 제목입니다.")
                .postContent("글 내용입니다.")
                .teamName("all")
                .build();
        MultipartFile[] multipartFiles = new MultipartFile[0];

        // when
        PostResponseDTO postResponse = postService.insertPost(postRequest, multipartFiles);

        // then
        PostResponseDTO findPost = postService.findById(postResponse.getPostId());
        assertThat(findPost.getPostTitle()).isEqualTo(postRequest.getPostTitle());
        assertThat(findPost.getPostContent()).isEqualTo(postRequest.getPostContent());
        assertThat(findPost.getTeamName()).isEqualTo("all");
        assertThat(findPost.getView()).isEqualTo(0L); // TestCode 작성으로 Entity -> DTO 변환로직에서 getView() 를 빼먹은걸 찾을 수 있었다...!!!!
    }
}
