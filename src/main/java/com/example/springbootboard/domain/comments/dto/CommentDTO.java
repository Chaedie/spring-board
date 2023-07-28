package com.example.springbootboard.domain.comments.dto;

import com.example.springbootboard.domain.comments.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CommentDTO {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private String commentContent;
        private Long userId;
        private Long postId;
        private String nickname;
        private String password;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private String commentContent;
        private Long userId;
        private String nickname;

        public Response(Comment comment) {
            this.commentContent = comment.getCommentContent();
            this.userId = comment.getUserId();
            this.nickname = comment.getNickname();
        }
    }
}
