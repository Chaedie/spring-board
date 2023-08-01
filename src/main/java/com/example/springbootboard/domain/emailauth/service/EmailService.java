package com.example.springbootboard.domain.emailauth.service;

import com.example.springbootboard.domain.users.dto.UserEmailRequestDTO;

public interface EmailService {

    void sendSimpleMessage(UserEmailRequestDTO userEmailRequestDTO) throws Exception;

    boolean isVerifiedCode(UserEmailRequestDTO userEmailRequestDTO);
}
