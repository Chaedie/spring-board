package com.example.springbootboard.service.impl;

import com.example.springbootboard.data.dto.PostRequestDTO;
import com.example.springbootboard.data.dto.PostResponseDTO;
import com.example.springbootboard.data.dto.UploadFileResponseDTO;
import com.example.springbootboard.service.PostService;
import com.example.springbootboard.service.UploadFileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PostServiceImplTest {

    // TEST CODE 는 filed Injection 해도 상관없다.
    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    PostService postService;


    @Test
    void insertPost() throws IOException {
        // given
        PostRequestDTO postRequest = PostRequestDTO.builder()
                .postTitle("글 제목입니다.")
                .postContent("글 내용입니다.")
                .teamName("all")
                .build();

        String filepath = "src/test/resources/images/";
        String filename = "test_image.png";

        MockMultipartFile file = new MockMultipartFile("images", new FileInputStream(filepath + filename));
        MultipartFile[] multipartFiles = new MultipartFile[1];
        multipartFiles[0] = file;

        // when
        PostResponseDTO postResponse = postService.insertPost(postRequest, multipartFiles);

        // then
        PostResponseDTO findPost = postService.findById(postResponse.getPostId());
        assertThat(findPost.getPostTitle()).isEqualTo(postRequest.getPostTitle());
        assertThat(findPost.getPostContent()).isEqualTo(postRequest.getPostContent());
        assertThat(findPost.getTeamName()).isEqualTo("all");
        assertThat(findPost.getView()).isEqualTo(0L); // TestCode 작성으로 Entity -> DTO 변환로직에서 getView() 를 빼먹은걸 찾을 수 있었다...!!!!
        UploadFileResponseDTO uploadFileResponse = uploadFileService.findByPostId(findPost.getPostId());

        System.out.println("findPost.getUploadFiles()   " + findPost.getUploadFiles());
        String[] fileKey = uploadFileResponse.getFileKey().split("_");
        assertThat(fileKey[fileKey.length - 1]).isEqualTo(filename);
    }
}
