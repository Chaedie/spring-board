package com.example.springbootboard.service.Impl;

import com.example.springbootboard.data.dto.CommentDTO;
import com.example.springbootboard.data.entity.Comment;
import com.example.springbootboard.data.entity.Post;
import com.example.springbootboard.data.repository.CommentRepository;
import com.example.springbootboard.data.repository.PostRepository;
import com.example.springbootboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

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
