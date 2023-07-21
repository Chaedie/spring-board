package com.example.springbootboard.service.Impl;

import com.example.springbootboard.data.dto.UserRequestDTO;
import com.example.springbootboard.data.dto.UserResponseDTO;
import com.example.springbootboard.data.entity.User;
import com.example.springbootboard.data.repository.TeamRepository;
import com.example.springbootboard.data.repository.UserRepository;
import com.example.springbootboard.global.error.Exception.ItemNotFoundException;
import com.example.springbootboard.global.error.errorcode.PostErrorCode;
import com.example.springbootboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final BCryptPasswordEncoder encoder;


    @Override
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
