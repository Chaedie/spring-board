package com.example.springbootboard.domain.users;

import com.example.springbootboard.domain.users.dto.UserRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                // .apply(springSecurity()) // csrf 토큰
                .build();
    }

    @Test
    @Transactional
    void 회원가입() throws Exception {
        // given
        String username = "username";
        String password = "password";
        String teamName = "all";
        String userEmail = "user@email.com";
        UserRequestDTO userRequest = UserRequestDTO.builder()
                .username(username)
                .password(password)
                .teamName(teamName)
                .userEmail(userEmail)
                .build();

        String url = "http://localhost:" + port + "/api/v1/users/join/api";
        System.out.println("UserControllerTest.회원가입");
        // when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andDo(print());
        User byUsername = userRepository.findByUsername(username).get();

        // then
        assertThat(byUsername.getUsername()).isEqualTo(username);
        assertThat(byUsername.getUserEmail()).isEqualTo(userEmail);
        assertThat(byUsername.getTeam().getTeamName()).isEqualTo(teamName);
    }

    @Test
    @Transactional
    void 회원가입_비밀번호가8자리이하면_실패한다() throws Exception {
        // given
        String username = "username";
        String password = "pass";
        String teamName = "all";
        String userEmail = "user@email.com";
        UserRequestDTO userRequest = UserRequestDTO.builder()
                .username(username)
                .password(password)
                .teamName(teamName)
                .userEmail(userEmail)
                .build();

        String url = "http://localhost:" + port + "/api/v1/users/join/api";
        System.out.println("UserControllerTest.회원가입");
        // when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest());
    }

}
