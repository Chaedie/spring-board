package com.example.springbootboard.service.Impl;

import com.example.springbootboard.data.dto.PostRequestDTO;
import com.example.springbootboard.data.dto.PostResponseDTO;
import com.example.springbootboard.data.dto.RedisPostDTO;
import com.example.springbootboard.data.entity.Post;
import com.example.springbootboard.data.entity.UploadFile;
import com.example.springbootboard.data.repository.PostRepository;
import com.example.springbootboard.data.repository.TeamRepository;
import com.example.springbootboard.global.error.Exception.AuthorizationException;
import com.example.springbootboard.global.error.Exception.ItemNotFoundException;
import com.example.springbootboard.global.error.errorcode.PostErrorCode;
import com.example.springbootboard.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final AwsS3ServiceImpl awsS3Service;
    private final PostRepository postRepository;
    private final TeamRepository teamRepository;
    private final RedisTemplate<String, RedisPostDTO> redisTemplate;

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public Page<PostResponseDTO> findAllWithPagination(String search, Pageable pageable) {
        Page<Post> pageList = postRepository.findByPostTitleContains(search, pageable);

        Page<PostResponseDTO> pageResponseDTOList = pageList.map(post -> new PostResponseDTO(post));
        return pageResponseDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostResponseDTO> findAllByTeamNameWithPagination(String teamName, String search, Pageable pageable) {
        Long teamId = teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new ItemNotFoundException(PostErrorCode.ITEM_NOT_FOUND))
                .getTeamId();

        Page<Post> pageList = postRepository.findByTeamTeamIdAndPostTitleContains(teamId, search, pageable);

        Page<PostResponseDTO> pageResponseDTOList = pageList.map(post -> new PostResponseDTO(post));
        return pageResponseDTOList;
    }

    @Override
    @Transactional(readOnly = true)
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

    /**
     * @param postId
     * @RedisKey postId::{postId}
     */
    @Override
    @Transactional
    public long incrementViewCount(Long postId) {
        String key = "postId::" + postId;
        RedisPostDTO cachedRedisPostDTO = redisTemplate.opsForValue().get(key);

        if (cachedRedisPostDTO == null) {
            Long view = postRepository.findById(postId).orElseThrow(ItemNotFoundException::new).getView();
            RedisPostDTO redisPostDTO = RedisPostDTO.builder()
                    .view(view + 1)
                    .build();
            redisTemplate.opsForValue().set(key, redisPostDTO);
            return view + 1;
        }
        Long cachedView = cachedRedisPostDTO.getView();
        cachedRedisPostDTO.setView(cachedView + 1);
        redisTemplate.opsForValue().set(key, cachedRedisPostDTO);
        return cachedView + 1;
    }

    @Override
    @Transactional
    public void syncViewCount() {
        ScanOptions scanOptions = ScanOptions.scanOptions().match("*").count(10).build();
        Cursor<byte[]> keys = redisTemplate.getConnectionFactory().getConnection().scan(scanOptions);

        while (keys.hasNext()) {
            String key = new String(keys.next());
            RedisPostDTO value = redisTemplate.opsForValue().get(key);
            Long postId = extractPostId(key);

            Post post = postRepository.findById(postId).get();
            post.setView(value.getView());
            postRepository.save(post);
        }

        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    private Long extractPostId(String key) {
        int index = key.indexOf(":");

        return Long.valueOf(key.substring(index + 2));
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
