package com.example.springbootboard.service;

import com.example.springbootboard.data.dto.UserEmailRequestDTO;

public interface EmailService {
    boolean isVerifiedCode(UserEmailRequestDTO userEmailRequestDTO);

    void sendSimpleMessage(UserEmailRequestDTO userEmailRequestDTO) throws Exception;
}
