package com.example.springbootboard.domain.posts;

import com.example.springbootboard.domain.posts.dto.PostRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                // .apply(springSecurity())
                .build();
    }

    @Test
    void visitAllPosts() throws Exception {
        // given
        for (Long postId = 1L; postId < 100; postId++) {
            System.out.println(postId);
            // when
            // then
            mvc.perform(get("/posts/detail?teamName=all&postId=" + postId))
                    // .param("teamName", "all")
                    // .param("postId", postId.toString()))
                    // .andExpect(status().isOk())
                    .andReturn();

        }
    }

    @Transactional
    @Test
    void createPost() throws Exception {
        // given
        String postTitle = "postTitle";
        String postContent = "postContent";
        String teamName = "all";
        Long userId = 1L;
        PostRequestDTO postRequest = PostRequestDTO.builder()
                .postTitle(postTitle)
                .postContent(postContent)
                .teamName(teamName)
                .userId(userId)
                .build();
        MockMultipartFile multipartFile1 = new MockMultipartFile(
                "fileName",
                "fileName",
                MediaType.IMAGE_JPEG_VALUE,
                "<<image jpeg>>".getBytes());
        MockMultipartFile multipartFile2 = new MockMultipartFile(
                "fileName2",
                "fileName2",
                MediaType.IMAGE_JPEG_VALUE,
                "<<image jpeg2>>".getBytes());
        String serializedPostRequest = objectMapper.writeValueAsString(postRequest);
        MockMultipartFile postRequestFile = new MockMultipartFile(
                "postRequest",
                "postRequest",
                "application/json",
                serializedPostRequest.getBytes());
        String url = "http://localhost:" + port + "/api/v1/boards/write/api";
        // when
        mvc.perform(multipart(url)
                        .file(postRequestFile)
                        // .file(multipartFile1)
                        // .file(multipartFile2)
                        .contentType("multipart/form-data")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
}
