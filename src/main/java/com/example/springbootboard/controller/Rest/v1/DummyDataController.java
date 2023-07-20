package com.example.springbootboard.controller.Rest.v1;

import com.example.springbootboard.data.entity.EmailAuth;
import com.example.springbootboard.data.repository.EmailAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/rest/v1/dummy")
@RequiredArgsConstructor
public class DummyDataController {

    private final EmailAuthRepository emailAuthRepository;

    @GetMapping("/insertAuthMail")
    @Transactional
    public String insert_100_000_AuthMail() {
        for (int i = 0; i < 100_000; i++) {
            emailAuthRepository.save(EmailAuth.builder()
                    .userEmail(UUID.randomUUID().toString())
                    .authCode(UUID.randomUUID().toString().substring(0, 8)).build());
        }
        return "done!";
    }
}
