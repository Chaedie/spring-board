package com.example.springbootboard.controller.Rest.v1;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.example.springbootboard.service.Impl.AwsS3ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rest/v1/aws/s3")
public class UploadController {
    @Autowired
    private AwsS3ServiceImpl awsS3Service;

    // @PostMapping("/upload")
    // public List<PutObjectResult> upload(@RequestParam("file") MultipartFile[] multipartFiles) {
    //     return awsS3Service.upload(multipartFiles, postId);
    // }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam String key) throws IOException {
        return awsS3Service.download(key);
    }

    @GetMapping("/list")
    public List<S3ObjectSummary> list() throws IOException {
        return awsS3Service.list();
    }
}
