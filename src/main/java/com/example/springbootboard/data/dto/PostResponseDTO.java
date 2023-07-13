package com.example.springbootboard.data.dto;

import com.example.springbootboard.data.entity.Post;
import com.example.springbootboard.data.entity.UploadFile;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long userId;

    private List<UploadFile> uploadFiles;

    public PostResponseDTO(Post post) {
        this.postId = post.getPostId();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.userId = post.getUserId();
        this.uploadFiles = post.getUploadFiles();
    }
}
