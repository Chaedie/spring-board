package com.example.springbootboard.data.repository;

import com.example.springbootboard.data.entity.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM email_auth " +
                    "WHERE user_email = :userEmail " +
                    "  AND auth_code = :authCode")
    List<EmailAuth> findByUserEmailAndAuthCode(@Param("userEmail") String userEmail, @Param("authCode") String authCode);
}
