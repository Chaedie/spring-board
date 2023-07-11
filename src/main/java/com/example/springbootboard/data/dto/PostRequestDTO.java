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
    private String postTitle;

    private String postContent;

    private Long userId;
}