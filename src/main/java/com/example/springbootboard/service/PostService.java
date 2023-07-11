package com.example.springbootboard.service;

import com.example.springbootboard.data.dto.PostRequestDTO;
import com.example.springbootboard.data.dto.PostResponseDTO;

import java.util.List;

public interface PostService {
    List<PostResponseDTO> findAll();

    PostResponseDTO findById(Long postId);

    PostResponseDTO insertPost(PostRequestDTO postRequestDTO);

    PostResponseDTO updatePost(PostRequestDTO postRequestDTO) throws Exception;

}
