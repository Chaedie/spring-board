package com.example.springbootboard.domain.posts;

import com.example.springbootboard.common.error.Exception.ItemNotFoundException;
import com.example.springbootboard.domain.teams.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    @DisplayName("Auditig 기능 적용 테스트")
    void findPost() {
        // given
        Post post = Post.builder()
                .postTitle("테스트 제목입니다.")
                .postContent("테스트 내용입니다.")
                .team(teamRepository.findById(1L).orElseThrow(ItemNotFoundException::new))
                .build();
        
        System.out.println("post = " + post);

        // when
        Post savedPost = postRepository.save(post);

        // then
        assertNotNull(savedPost.getCreatedAt());
        assertNotNull(savedPost.getUpdatedAt());
    }
}
