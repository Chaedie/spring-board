package com.example.springbootboard.data.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "email_auth")
public class EmailAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_email", nullable = false, length = 40)
    private String userEmail;

    @Column(name = "auth_code", nullable = false, length = 16)
    private String authCode;
}
