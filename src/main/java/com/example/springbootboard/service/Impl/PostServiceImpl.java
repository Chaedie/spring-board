package com.example.springbootboard.service.Impl;

import com.example.springbootboard.data.dto.PostRequestDTO;
import com.example.springbootboard.data.dto.PostResponseDTO;
import com.example.springbootboard.data.entity.Post;
import com.example.springbootboard.data.entity.UploadFile;
import com.example.springbootboard.data.repository.PostRepository;
import com.example.springbootboard.data.repository.TeamRepository;
import com.example.springbootboard.data.repository.UploadFileRepository;
import com.example.springbootboard.global.error.Exception.AuthorizationException;
import com.example.springbootboard.global.error.Exception.ItemNotFoundException;
import com.example.springbootboard.global.error.errorcode.PostErrorCode;
import com.example.springbootboard.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final AwsS3ServiceImpl awsS3Service;
    private final PostRepository postRepository;
    private final UploadFileRepository uploadFileRepository;
    private final TeamRepository teamRepository;

    @Override
    @Transactional
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
    @Transactional
    public Page<PostResponseDTO> findAllWithPagination(String search, Pageable pageable) {
        Page<Post> pageList = postRepository.findByPostTitleContains(search, pageable);

        Page<PostResponseDTO> pageResponseDTOList = pageList.map(post -> new PostResponseDTO(post));
        return pageResponseDTOList;
    }

    @Override
    @Transactional
    public Page<PostResponseDTO> findAllByTeamNameWithPagination(String teamName, String search, Pageable pageable) {
        Long teamId = teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new ItemNotFoundException(PostErrorCode.ITEM_NOT_FOUND))
                .getTeamId();

        Page<Post> pageList = postRepository.findByTeamTeamIdAndPostTitleContains(teamId, search, pageable);

        Page<PostResponseDTO> pageResponseDTOList = pageList.map(post -> new PostResponseDTO(post));
        return pageResponseDTOList;
    }

    @Override
    @Transactional
    public PostResponseDTO findById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ItemNotFoundException(PostErrorCode.ITEM_NOT_FOUND));

        return new PostResponseDTO(post);
    }

    @Override
    @Transactional
    public PostResponseDTO updatePost(PostRequestDTO postRequestDTO) {
        Optional<Post> selectedPost = postRepository.findById(postRequestDTO.getPostId());

        Post updatedPost;
        if (selectedPost.isPresent()) {
            Post post = selectedPost.get();

            // 비회원일 경우 ID PW 체크
            if (!checkNicknamePassword(post, postRequestDTO)) {
                throw new AuthorizationException(PostErrorCode.AUTHORIZATION_FAIL);
            }

            post.setPostTitle(postRequestDTO.getPostTitle());
            post.setPostContent(postRequestDTO.getPostContent());

            updatedPost = postRepository.save(post);

        } else {
            throw new ItemNotFoundException(PostErrorCode.ITEM_NOT_FOUND);
        }

        return new PostResponseDTO(updatedPost);
    }

    private boolean checkNicknamePassword(Post post, PostRequestDTO postRequestDTO) {
        String storedNickname = post.getNickname();
        String storedPassword = post.getPassword();

        // 회원일 경우 return true;
        if (storedNickname == null && storedPassword == null) {
            return true;
        }
        // 비회원이면서 둘다 맞으면 true;
        if (storedNickname.equals(postRequestDTO.getNickname()) && storedPassword.equals(postRequestDTO.getPassword())) {
            return true;
        }
        // 아니면 false
        return false;
    }

    @Override
    @Transactional
    public void delete(Long postId) {
        Optional<Post> selectedPost = postRepository.findById(postId);

        if (selectedPost.isPresent()) {
            Post post = selectedPost.get();

            postRepository.delete(post);
        } else {
            throw new ItemNotFoundException(PostErrorCode.ITEM_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public PostResponseDTO insertPost(PostRequestDTO postRequestDTO, MultipartFile[] multipartFiles) {
        /**
         * 1. Post 엔티티 생성
         */
        Post post = Post.builder()
                .postTitle(postRequestDTO.getPostTitle())
                .postContent(postRequestDTO.getPostContent())
                .userId(postRequestDTO.getUserId())
                .nickname(postRequestDTO.getNickname())
                .password(postRequestDTO.getPassword())
                .team(teamRepository.findByTeamName(postRequestDTO.getTeamName())
                        .orElseThrow(() -> new ItemNotFoundException(PostErrorCode.ITEM_NOT_FOUND)))
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
