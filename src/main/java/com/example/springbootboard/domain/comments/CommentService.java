package com.example.springbootboard.domain.comments;

import com.example.springbootboard.domain.comments.dto.CommentDTO;
import com.example.springbootboard.domain.posts.Post;
import com.example.springbootboard.domain.posts.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

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
