package com.example.springbootboard.domain.uploadfiles.dto;

import com.example.springbootboard.domain.uploadfiles.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UploadFileResponseDTO {

    private String fileUrl;

    private String fileKey;

    @Builder
    public UploadFileResponseDTO(UploadFile uploadFile) {
        this.fileUrl = uploadFile.getFileUrl();
        this.fileKey = uploadFile.getFileKey();
    }
}
