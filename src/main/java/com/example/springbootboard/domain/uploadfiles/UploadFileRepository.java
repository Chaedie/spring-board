package com.example.springbootboard.domain.uploadfiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {

    @Query(value = "select u from UploadFile u JOIN u.post p where p.postId = :postId")
    List<UploadFile> findByPostId(Long postId);

}
