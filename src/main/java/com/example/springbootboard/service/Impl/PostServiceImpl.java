package com.example.springbootboard.service.Impl;

import com.example.springbootboard.data.dto.PostResponseDTO;
import com.example.springbootboard.data.entity.Post;
import com.example.springbootboard.data.repository.PostRepository;
import com.example.springbootboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public List<PostResponseDTO> findAll() {
        List<Post> postList = postRepository.findAll();
        List<PostResponseDTO> postResponseDTOList = new ArrayList<>();
        for (Post post : postList) {
            PostResponseDTO postResponseDTO = new PostResponseDTO(post);
            postResponseDTOList.add(postResponseDTO);
        }

        return postResponseDTOList;
    }
}
