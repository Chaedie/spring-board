package com.example.springbootboard.domain.posts;

import com.example.springbootboard.common.BaseTimeEntity;
import com.example.springbootboard.domain.comments.Comment;
import com.example.springbootboard.domain.posts.dto.PostRequestDTO;
import com.example.springbootboard.domain.teams.Team;
import com.example.springbootboard.domain.uploadfiles.UploadFile;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "posts")
public class Post extends BaseTimeEntity {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "title", nullable = false, length = 100)
    private String postTitle;

    @Column(name = "content", nullable = false, length = 5000)
    private String postContent;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "nickname", length = 30)
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "view", columnDefinition = "BIGINT DEFAULT 0")
    private Long view;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<UploadFile> uploadFiles = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.view = this.view == null ? 0 : this.view;
    }

    public void updatePostTitleAndContent(PostRequestDTO postRequestDTO) {
        this.postTitle = postRequestDTO.getPostTitle();
        this.postContent = postRequestDTO.getPostContent();
    }

    public void updateView(Long view) {
        this.view = view;
    }

    public void incrementView() {
        this.view++;
    }
}
