package com.example.springbootboard.domain.posts.dto;

import com.example.springbootboard.domain.comments.Comment;
import com.example.springbootboard.domain.posts.Post;
import com.example.springbootboard.domain.uploadfiles.UploadFile;
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
    private String teamName;
    private Long view;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ToString.Exclude
    private List<UploadFile> uploadFiles;
    @ToString.Exclude
    private List<Comment> comments;

    public PostResponseDTO(Post post) {
        this.postId = post.getPostId();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.userId = post.getUserId();
        this.nickname = post.getNickname();
        this.teamName = post.getTeam().getTeamName();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.uploadFiles = post.getUploadFiles();
        this.comments = post.getComments();
        this.view = post.getView();
    }
}
