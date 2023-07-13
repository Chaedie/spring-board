package com.example.springbootboard.data.dto;

import com.example.springbootboard.data.entity.UploadFile;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PostRequestDTO {

    private Long postId;

    private String postTitle;

    private String postContent;

    private Long userId;

    private List<UploadFile> uploadFiles;
}
