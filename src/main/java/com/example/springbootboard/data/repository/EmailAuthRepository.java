package com.example.springbootboard.data.repository;

import com.example.springbootboard.data.entity.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, Long> {

    Optional<EmailAuth> findByUserEmailAndAuthCode(String userEmail, String authCode);
}
