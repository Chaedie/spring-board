package com.example.springbootboard.service.Impl;

import com.example.springbootboard.data.dto.PostRequestDTO;
import com.example.springbootboard.data.dto.PostResponseDTO;
import com.example.springbootboard.data.entity.Post;
import com.example.springbootboard.data.repository.PostRepository;
import com.example.springbootboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public List<PostResponseDTO> findAll() {
        List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "postId"));
        List<PostResponseDTO> postResponseDTOList = new ArrayList<>();
        for (Post post : postList) {
            PostResponseDTO postResponseDTO = new PostResponseDTO(post);
            postResponseDTOList.add(postResponseDTO);
        }

        return postResponseDTOList;
    }

    @Override
    public PostResponseDTO findById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(EntityNotFoundException::new);

        return new PostResponseDTO(post);
    }

    @Override
    public PostResponseDTO updatePost(PostRequestDTO postRequestDTO) throws Exception {
        Optional<Post> selectedPost = postRepository.findById(postRequestDTO.getPostId());

        Post updatedPost;
        if (selectedPost.isPresent()) {
            Post post = selectedPost.get();

            post.setPostTitle(postRequestDTO.getPostTitle());
            post.setPostContent(postRequestDTO.getPostContent());

            updatedPost = postRepository.save(post);

        } else {
            throw new EntityNotFoundException();
        }

        return new PostResponseDTO(updatedPost);
    }

    @Override
    public PostResponseDTO insertPost(PostRequestDTO postRequestDTO) {
        Post savedPost = postRepository.save(new Post().builder()
                .postTitle(postRequestDTO.getPostTitle())
                .postContent(postRequestDTO.getPostContent())
                .userId(postRequestDTO.getUserId())
                .build());

        return new PostResponseDTO(savedPost);
    }
}
