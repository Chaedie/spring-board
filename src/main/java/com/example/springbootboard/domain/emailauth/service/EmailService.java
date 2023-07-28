package com.example.springbootboard.domain.emailauth.service;

import com.example.springbootboard.domain.users.dto.UserEmailRequestDTO;

public interface EmailService {
    boolean isVerifiedCode(UserEmailRequestDTO userEmailRequestDTO);

    void sendSimpleMessage(UserEmailRequestDTO userEmailRequestDTO) throws Exception;
}
