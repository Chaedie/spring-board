package com.example.springbootboard.data.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "content")
    private String commentContent;

    @Column(name = "user_id")
    private Long userId;

    private String nickname;
    private String password;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
