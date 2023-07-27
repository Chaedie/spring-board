package com.example.springbootboard.service.Impl;

import com.example.springbootboard.data.dto.UploadFileResponseDTO;
import com.example.springbootboard.data.entity.UploadFile;
import com.example.springbootboard.data.repository.UploadFileRepository;
import com.example.springbootboard.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UploadFileServiceImpl implements UploadFileService {

    private final UploadFileRepository uploadFileRepository;

    @Override
    public UploadFileResponseDTO findByPostId(Long postId) {
        List<UploadFile> list = uploadFileRepository.findByPostId(postId);

        return new UploadFileResponseDTO(list.get(0));
    }
}
