package com.example.springbootboard.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEmailRequestDTO {
    private String userEmail;
    private String authCode;
}
