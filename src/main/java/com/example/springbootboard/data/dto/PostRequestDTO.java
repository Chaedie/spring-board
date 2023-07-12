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

    // private List<String> fileUrlList;

    private String fileUrl;
}
