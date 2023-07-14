package com.example.springbootboard.service;

import com.example.springbootboard.data.dto.CommentDTO;

public interface CommentService {
    CommentDTO.Response insertComment(CommentDTO.Request commentRequestDTO);
    
}
