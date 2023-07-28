package com.example.springbootboard.domain.emailauth;

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

    boolean existsByUserEmailAndAuthCode(String userEmail, String authCode);

    void deleteByUserEmailAndAuthCode(String userEmail, String authCode);
}
