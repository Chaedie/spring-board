package com.example.springbootboard.domain.emailauth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEmailRequestDTO {
    @NotBlank
    @Email
    private String userEmail;
    @NotBlank
    private String authCode;
}
