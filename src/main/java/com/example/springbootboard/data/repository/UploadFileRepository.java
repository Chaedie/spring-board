package com.example.springbootboard.data.repository;

import com.example.springbootboard.data.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
}
