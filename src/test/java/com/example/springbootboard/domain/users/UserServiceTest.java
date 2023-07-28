package com.example.springbootboard.domain.users;

import com.example.springbootboard.domain.users.dto.UserRequestDTO;
import com.example.springbootboard.domain.users.dto.UserResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    void 회원가입() {
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

        // when
        UserResponseDTO userResponse = userService.signUpUser(userRequest);
        User byUsername = userRepository.findByUsername(username).get();
        System.out.println("byUsername = " + byUsername);

        // then
        assertThat(userResponse.getUsername()).isEqualTo(username);
        assertThat(userResponse.getUserEmail()).isEqualTo(userEmail);
        assertThat(userResponse.getTeam().getTeamName()).isEqualTo(teamName);
    }

    
}
