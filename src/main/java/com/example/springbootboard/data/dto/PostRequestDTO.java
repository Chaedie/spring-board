package com.example.springbootboard.data.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PostRequestDTO {

    private Long postId;

    @NotBlank
    private String postTitle;

    @NotBlank
    private String postContent;
    
    private Long userId;
    private String teamName;

    private String nickname;
    private String password;
}
