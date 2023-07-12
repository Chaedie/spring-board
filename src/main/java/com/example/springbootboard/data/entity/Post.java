package com.example.springbootboard.data.entity;

import lombok.*;

import javax.persistence.*;

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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "title")
    private String postTitle;

    @Column(name = "content")
    private String postContent;

    // 1차시도
    // @ElementCollection
    // @CollectionTable(name = "upload_files", joinColumns = @JoinColumn(name = "post_id"))
    // private List<UploadFile> uploadFiles = new ArrayList<>();

    // 2차시도
    // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    // @JoinColumn(name = "post_id")
    // private List<UploadFile> uploadFiles = new ArrayList<>();

    // 3차 우선 1개씩 저장하자.
    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "user_id")
    private Long userId;
}
