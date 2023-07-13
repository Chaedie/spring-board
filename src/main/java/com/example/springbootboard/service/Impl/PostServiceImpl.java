package com.example.springbootboard.service.Impl;

import com.example.springbootboard.data.dto.PostRequestDTO;
import com.example.springbootboard.data.dto.PostResponseDTO;
import com.example.springbootboard.data.entity.Post;
import com.example.springbootboard.data.entity.UploadFile;
import com.example.springbootboard.data.repository.PostRepository;
import com.example.springbootboard.data.repository.UploadFileRepository;
import com.example.springbootboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final AwsS3ServiceImpl awsS3Service;
    private final PostRepository postRepository;
    private final UploadFileRepository uploadFileRepository;

    @Autowired
    public PostServiceImpl(AwsS3ServiceImpl awsS3Service, PostRepository postRepository, UploadFileRepository uploadFileRepository) {
        this.awsS3Service = awsS3Service;
        this.postRepository = postRepository;
        this.uploadFileRepository = uploadFileRepository;
    }


    @Override
    public List<PostResponseDTO> findAll() {
        List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "postId"));
        List<PostResponseDTO> postResponseDTOList = new ArrayList<>();
        for (Post post : postList) {
            PostResponseDTO postResponseDTO = new PostResponseDTO(post);
            postResponseDTOList.add(postResponseDTO);
        }

        return postResponseDTOList;
    }

    @Override
    public Page<PostResponseDTO> findAllWithPagination(String search, Pageable pageable) {
        Page<Post> pageList = postRepository.findByPostTitleContains(search, pageable);

        Page<PostResponseDTO> pageResponseDTOList = pageList.map(post -> new PostResponseDTO(post));
        return pageResponseDTOList;
    }

    @Override
    public PostResponseDTO findById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(EntityNotFoundException::new);

        return new PostResponseDTO(post);
    }

    @Override
    public PostResponseDTO updatePost(PostRequestDTO postRequestDTO) throws Exception {
        Optional<Post> selectedPost = postRepository.findById(postRequestDTO.getPostId());

        Post updatedPost;
        if (selectedPost.isPresent()) {
            Post post = selectedPost.get();

            post.setPostTitle(postRequestDTO.getPostTitle());
            post.setPostContent(postRequestDTO.getPostContent());

            updatedPost = postRepository.save(post);

        } else {
            throw new EntityNotFoundException();
        }

        return new PostResponseDTO(updatedPost);
    }

    @Override
    public void delete(Long postId) throws Exception {
        Optional<Post> selectedPost = postRepository.findById(postId);

        if (selectedPost.isPresent()) {
            Post post = selectedPost.get();

            postRepository.delete(post);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public PostResponseDTO insertPost(PostRequestDTO postRequestDTO, MultipartFile[] multipartFiles) {
        /**
         * 1. Post 엔티티 생성
         */
        Post post = Post.builder()
                .postTitle(postRequestDTO.getPostTitle())
                .postContent(postRequestDTO.getPostContent())
                .userId(postRequestDTO.getUserId())
                .uploadFiles(new ArrayList<>())
                .build();

        /**
         * 2. S3 Upload
         * 2.1. UploadFile 엔티티 생성
         * 2.2. post <-> uploadFiles 양방향 연관관계 매핑
         */
        awsS3Service.upload(multipartFiles).stream()
                .forEach(url -> {
                    UploadFile uploadFile = UploadFile.builder()
                            .fileUrl(url)
                            .build();
                    post.getUploadFiles().add(uploadFile);
                    uploadFile.setPost(post);
                });

        return new PostResponseDTO(postRepository.save(post));
    }
}
