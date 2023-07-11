package com.example.springbootboard.service;

import com.example.springbootboard.data.dto.PostResponseDTO;

import java.util.List;

public interface PostService {
    List<PostResponseDTO> findAll();
}
