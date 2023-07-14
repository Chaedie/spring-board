package com.example.springbootboard.data.dto;

import com.example.springbootboard.data.entity.Comment;
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
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private String commentContent;
        private Long userId;

        public Response(Comment comment) {
            this.commentContent = comment.getCommentContent();
            this.userId = comment.getUserId();
        }
    }
}
