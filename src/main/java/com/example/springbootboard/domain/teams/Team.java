package com.example.springbootboard.domain.teams;

import com.example.springbootboard.domain.posts.Post;
import com.example.springbootboard.domain.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Team {

    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column(name = "team_name", unique = true, length = 100)
    private String teamName;

    @Column(name = "team_korean_name", unique = true, length = 100)
    private String teamKoreanName;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    private List<User> users = new ArrayList<>();

}
