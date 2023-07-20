package com.example.springbootboard.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "posts")
public class Post extends BaseEntity {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "title", nullable = false)
    private String postTitle;

    @Column(name = "content", nullable = false)
    private String postContent;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "view", columnDefinition = "BIGINT DEFAULT 0")
    private Long view;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
    private List<UploadFile> uploadFiles = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.view = this.view == null ? 0 : this.view;
    }
}
