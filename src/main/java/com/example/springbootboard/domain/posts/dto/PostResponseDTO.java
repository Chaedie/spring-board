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
@NoArgsConstructor
@EqualsAndHashCode
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

    @Builder
    public PostResponseDTO(Post post) {
        this.postId = post.getPostId();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.userId = post.getUserId();
        this.nickname = post.getNickname();
        this.teamName = post.getTeam().getTeamName();
        this.view = post.getView();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.uploadFiles = post.getUploadFiles();
        this.comments = post.getComments();
    }

    // 정적 팩토리 메서드 사용
    public static PostResponseDTO from(Post post) {
        return new PostResponseDTO(post);
    }
}
