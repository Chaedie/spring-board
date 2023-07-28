package com.example.springbootboard.domain.uploadfiles;

import com.example.springbootboard.domain.uploadfiles.dto.UploadFileResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UploadFileService {

    private final UploadFileRepository uploadFileRepository;

    public UploadFileResponseDTO findByPostId(Long postId) {
        List<UploadFile> list = uploadFileRepository.findByPostId(postId);

        return new UploadFileResponseDTO(list.get(0));
    }
}
