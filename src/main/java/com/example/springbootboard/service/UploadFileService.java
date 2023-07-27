package com.example.springbootboard.service;

import com.example.springbootboard.data.dto.UploadFileResponseDTO;

public interface UploadFileService {

    UploadFileResponseDTO findByPostId(Long postId);
}
