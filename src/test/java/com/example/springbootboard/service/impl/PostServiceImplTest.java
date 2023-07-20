package com.example.springbootboard.service.impl;


import com.example.springbootboard.service.Impl.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PostServiceImpl postService;

    @Test
    @Transactional
    public void syncViewCountTest() {
        postService.syncViewCount();
    }
}
