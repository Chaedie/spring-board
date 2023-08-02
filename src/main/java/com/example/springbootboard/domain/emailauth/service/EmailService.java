package com.example.springbootboard.domain.emailauth.service;

import com.example.springbootboard.domain.emailauth.dto.UserEmailRequestDTO;

public interface EmailService {

    void sendSimpleMessage(UserEmailRequestDTO userEmailRequestDTO) throws Exception;

    boolean isVerifiedCode(UserEmailRequestDTO userEmailRequestDTO);
}
