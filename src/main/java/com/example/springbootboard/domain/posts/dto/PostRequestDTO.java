package com.example.springbootboard.domain.posts.dto;

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
    @NotBlank
    private String teamName;

    private String nickname;
    private String password;
}
