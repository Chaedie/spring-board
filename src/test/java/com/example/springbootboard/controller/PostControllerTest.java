package com.example.springbootboard.controller;

import com.example.springbootboard.service.Impl.PostServiceImpl;
import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(PostController.class)
@Import(SecurityConfig.class)
public class PostControllerTest {

    @Autowired
    WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostServiceImpl postService;

    @Test
    void visitAllPosts() throws Exception {
        // given
        for (Long postId = 1L; postId < 100; postId++) {
            System.out.println(postId);
            // when
            // then
            mockMvc.perform(get("/posts/detail?teamName=all&postId=" + postId))
                    // .param("teamName", "all")
                    // .param("postId", postId.toString()))
                    // .andExpect(status().isOk())
                    .andReturn();

        }
    }
}
