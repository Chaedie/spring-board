package com.example.springbootboard.domain.emailauth.service;

import com.example.springbootboard.domain.emailauth.EmailAuth;
import com.example.springbootboard.domain.emailauth.EmailAuthRepository;
import com.example.springbootboard.domain.emailauth.dto.UserEmailRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailAuthRepository emailAuthRepository;
    private final EmailUtils emailUtils;

    @Override
    @Transactional
    public void sendSimpleMessage(UserEmailRequestDTO userEmailRequestDTO) throws Exception {
        String authCode = emailUtils.createAuthCode();
        String userEmail = userEmailRequestDTO.getUserEmail();
        emailAuthRepository.save(EmailAuth.builder()
                .userEmail(userEmail)
                .authCode(authCode)
                .build());

        emailUtils.sendMail(userEmail, authCode);
    }

    @Override
    @Transactional
    public boolean isVerifiedCode(UserEmailRequestDTO userEmailRequestDTO) {
        List<EmailAuth> selectedEmailAuth = emailAuthRepository.findByUserEmailAndAuthCode(userEmailRequestDTO.getUserEmail(), userEmailRequestDTO.getAuthCode());
        boolean isExists = selectedEmailAuth.size() > 0;
        if (isExists) {
            emailAuthRepository.deleteByUserEmailAndAuthCode(userEmailRequestDTO.getUserEmail(), userEmailRequestDTO.getAuthCode());
        }

        return isExists;
    }
}
