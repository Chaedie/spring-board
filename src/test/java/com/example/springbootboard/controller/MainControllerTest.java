package com.example.springbootboard.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void 메인페이지_로딩() {
        // when
        String expectedBody = "Spring 게시판 !!!";
        String url = "/";

        // then
        String actual = restTemplate.getForObject(url, String.class);

        assertThat(actual).contains(expectedBody);
    }


    @Test
    void 비회원일경우_비회원입니다_출력() {
        // when
        String expectedBody = "비회원입니다";
        String url = "/";

        // then
        String actual = restTemplate.getForObject(url, String.class);

        assertThat(actual).contains(expectedBody);
    }
}
