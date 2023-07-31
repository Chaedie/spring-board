package com.example.springbootboard.domain.posts.dto;

import com.example.springbootboard.domain.posts.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RedisPostDTO {

    /**
     * Redis의 postId::{postId}에 해당 DTO를 넣는다.
     */
    private Long view;

    @Builder
    public RedisPostDTO(Post post) {
        this.view = post.getView();
    }
    
}
