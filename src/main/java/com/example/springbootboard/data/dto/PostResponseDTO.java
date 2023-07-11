package com.example.springbootboard.data.dto;

import com.example.springbootboard.data.entity.Post;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PostResponseDTO {
    private Long postId;

    private String postTitle;

    private String postContent;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public PostResponseDTO(Post post) {
    }
}
