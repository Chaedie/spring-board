package com.example.springbootboard.domain.comments;

import com.example.springbootboard.common.BaseTimeEntity;
import com.example.springbootboard.domain.posts.Post;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "comments")
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "content", length = 1000)
    private String commentContent;

    @Column(name = "user_id")
    private Long userId;

    @Column(length = 30)
    private String nickname;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
