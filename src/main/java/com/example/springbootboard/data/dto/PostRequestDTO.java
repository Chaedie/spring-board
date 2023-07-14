package com.example.springbootboard.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PostRequestDTO {

    private Long postId;
    private String postTitle;
    private String postContent;
    private Long userId;
    private String teamName;

    private String nickname;
    private String password;
}
