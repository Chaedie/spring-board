package com.example.springbootboard.domain.posts.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class RedisPostDTO {
    /**
     * Redis의 postId::{postId}에 해당 DTO를 넣는다.
     */
    private Long view;

}
