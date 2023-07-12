package com.example.springbootboard.data.dto;

import com.example.springbootboard.data.entity.Post;
import lombok.*;

import java.time.LocalDateTime;

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

    // private List<UploadFile> uploadFiles;

    private String fileUrl;

    public PostResponseDTO(Post post) {
        this.postId = post.getPostId();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.userId = post.getUserId();
        // this.uploadFiles = post.getUploadFiles();
        this.fileUrl = post.getFileUrl();
    }
}
