package com.example.springbootboard.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UserRequestDTO {

    private String username;
    private String userEmail;
    private String password;
    private String teamName;
}
