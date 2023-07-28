package com.example.springbootboard.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void 메인페이지_로딩() {
        // given
        String expectedBody = "Spring 게시판 !!!";
        String url = "/";

        // when
        String actual = restTemplate.getForObject(url, String.class);

        // then
        assertThat(actual).contains(expectedBody);
    }


    @Test
    void 비회원일경우_비회원입니다_출력() {
        // given
        String expectedBody = "비회원입니다";
        String url = "/";

        // when
        String actual = restTemplate.getForObject(url, String.class);

        // then
        assertThat(actual).contains(expectedBody);
    }
}
