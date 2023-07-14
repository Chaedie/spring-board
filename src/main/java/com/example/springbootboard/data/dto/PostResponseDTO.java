package com.example.springbootboard.data.dto;

import com.example.springbootboard.data.entity.Comment;
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
    private Long userId;
    private String nickname;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<UploadFile> uploadFiles;
    private List<Comment> comments;

    public PostResponseDTO(Post post) {
        this.postId = post.getPostId();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.userId = post.getUserId();
        this.nickname = post.getNickname();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.uploadFiles = post.getUploadFiles();
        this.comments = post.getComments();
    }
}
