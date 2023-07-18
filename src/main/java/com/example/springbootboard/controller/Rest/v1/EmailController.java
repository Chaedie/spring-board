package com.example.springbootboard.controller.Rest.v1;

import com.example.springbootboard.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/v1/mail")
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/send")
    public ResponseEntity<Object> send(String userEmail) throws Exception {

        String confirm = emailService.sendSimpleMessage(userEmail);

        return ResponseEntity.ok(confirm);
    }
}
