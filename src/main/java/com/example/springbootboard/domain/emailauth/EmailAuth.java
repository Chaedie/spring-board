package com.example.springbootboard.domain.emailauth;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@ToString
@Table(name = "email_auth")
public class EmailAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_email", nullable = false, length = 40)
    @Email
    private String userEmail;

    @Column(name = "auth_code", nullable = false, length = 16)
    private String authCode;
}
