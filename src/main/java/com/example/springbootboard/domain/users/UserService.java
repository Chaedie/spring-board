package com.example.springbootboard.domain.users;

import com.example.springbootboard.common.error.Exception.ItemNotFoundException;
import com.example.springbootboard.common.error.errorcode.PostErrorCode;
import com.example.springbootboard.domain.teams.TeamRepository;
import com.example.springbootboard.domain.users.dto.UserRequestDTO;
import com.example.springbootboard.domain.users.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public UserResponseDTO signUpUser(UserRequestDTO userRequestDTO) {
        User user = User.builder()
                .username(userRequestDTO.getUsername())
                .userEmail(userRequestDTO.getUserEmail())
                .password(encoder.encode(userRequestDTO.getPassword()))
                .team(teamRepository.findByTeamName(userRequestDTO.getTeamName())
                        .orElseThrow(() -> new ItemNotFoundException(PostErrorCode.ITEM_NOT_FOUND)))
                .build();

        userRepository.save(user);

        return new UserResponseDTO(user);
    }
}
