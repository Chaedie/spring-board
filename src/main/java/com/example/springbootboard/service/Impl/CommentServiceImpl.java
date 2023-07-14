package com.example.springbootboard.service.Impl;

import com.example.springbootboard.data.dto.CommentDTO;
import com.example.springbootboard.data.entity.Comment;
import com.example.springbootboard.data.entity.Post;
import com.example.springbootboard.data.repository.CommentRepository;
import com.example.springbootboard.data.repository.PostRepository;
import com.example.springbootboard.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @Override
    @Transactional
    public CommentDTO.Response insertComment(CommentDTO.Request commentRequestDTO) {
        Long postId = commentRequestDTO.getPostId();
        Post post = Optional.ofNullable(postRepository.findById(postId))
                .orElseThrow(EntityNotFoundException::new).get();

        Comment comment = Comment.builder()
                .post(post)
                .commentContent(commentRequestDTO.getCommentContent())
                .userId(commentRequestDTO.getUserId())
                .nickname(commentRequestDTO.getNickname())
                .password(commentRequestDTO.getPassword())
                .build();
        commentRepository.save(comment);

        return new CommentDTO.Response(comment);
    }
}