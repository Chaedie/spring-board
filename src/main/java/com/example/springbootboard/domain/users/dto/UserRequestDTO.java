package com.example.springbootboard.domain.users.dto;

import com.example.springbootboard.config.annotation.Password;
import com.example.springbootboard.config.annotation.UserName;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UserRequestDTO {

    @UserName
    private String username;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email
    private String userEmail;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Password
    private String password;

    @NotBlank(message = "팀네임은 필수입니다.")
    private String teamName;
}
