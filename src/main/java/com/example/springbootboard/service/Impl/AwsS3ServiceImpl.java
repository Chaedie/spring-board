package com.example.springbootboard.service.Impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AwsS3ServiceImpl {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // private final UploadFileRepository uploadFileRepository;
    @Autowired
    public AwsS3ServiceImpl(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    // @Autowired
    // public AwsS3ServiceImpl(AmazonS3Client amazonS3Client, UploadFileRepository uploadFileRepository) {
    //     this.amazonS3Client = amazonS3Client;
    //     this.uploadFileRepository = uploadFileRepository;
    // }

    private String upload(String filePath, String uploadKey) throws FileNotFoundException {
        return upload(new FileInputStream(filePath), uploadKey);
    }

    // private PutObjectResult upload(InputStream inputStream, String uploadKey) {
    //     PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, uploadKey, inputStream, new ObjectMetadata());
    //     PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);
    //     IOUtils.closeQuietly(inputStream, null);
    //     return putObjectResult;
    // }

    private String upload(InputStream inputStream, String uploadKey) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, uploadKey, inputStream, new ObjectMetadata());
        PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);
        IOUtils.closeQuietly(inputStream, null);
        return amazonS3Client.getUrl(bucket, uploadKey).toString();
    }


    // public List<PutObjectResult> upload(MultipartFile[] multipartFiles) {
    //     List<PutObjectResult> putObjectResults = new ArrayList<>();
    //     Arrays.stream(multipartFiles)
    //             .filter(multipartFile -> !StringUtils.isEmpty(multipartFile.getOriginalFilename()))
    //             .forEach(multipartFile -> {
    //                 try {
    //                     putObjectResults.add(upload(multipartFile.getInputStream(), createStoreFileName(multipartFile.getOriginalFilename())));
    //                 } catch (IOException e) {
    //                     e.printStackTrace();
    //                 }
    //             });
    //     return putObjectResults;
    // }

    public String upload(MultipartFile multipartFile) {
        String storedUrl = null;
        try {
            storedUrl = upload(multipartFile.getInputStream(), createStoreFileName(multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("storedUrl = " + storedUrl);
        return storedUrl;
    }

    public List<String> upload(MultipartFile[] multipartFiles) {
        List<String> storeUrlList = new ArrayList<>();
        Arrays.stream(multipartFiles)
                .filter(multipartFile -> !StringUtils.isEmpty(multipartFile.getOriginalFilename()))
                .forEach(multipartFile -> {
                    try {
                        storeUrlList.add(upload(multipartFile.getInputStream(), createStoreFileName(multipartFile.getOriginalFilename())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        return storeUrlList;
    }


    public ResponseEntity<byte[]> download(String key) throws IOException {
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);
        S3Object s3Object = amazonS3Client.getObject(getObjectRequest);
        S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(objectInputStream);
        String fileName = URLEncoder.encode(key, "UTF-8").replaceAll("\\+", "%20");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

    public List<S3ObjectSummary> list() {
        ObjectListing objectListing = amazonS3Client.listObjects(new ListObjectsRequest().withBucketName(bucket));
        List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();
        return s3ObjectSummaries;
    }

    private String createStoreFileName(String originalFileName) {
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }
}
