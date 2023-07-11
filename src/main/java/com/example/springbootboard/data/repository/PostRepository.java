package com.example.springbootboard.data.repository;

import com.example.springbootboard.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
