package com.example.springbootboard.service;

import com.example.springbootboard.data.dto.PostRequestDTO;
import com.example.springbootboard.data.dto.PostResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    List<PostResponseDTO> findAll();

    Page<PostResponseDTO> findAllWithPagination(String search, Pageable pageable);

    PostResponseDTO findById(Long postId);

    PostResponseDTO insertPost(PostRequestDTO postRequestDTO, MultipartFile[] multipartFiles);

    PostResponseDTO updatePost(PostRequestDTO postRequestDTO) throws Exception;

    void delete(Long postId) throws Exception;

}
