package com.example.springbootboard.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByPostTitleContains(String postTitle, Pageable pageable);

    Page<Post> findByTeamTeamIdAndPostTitleContains(Long teamId, String postTitle, Pageable pageable);

}
