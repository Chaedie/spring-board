package com.example.springbootboard.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Team {

    @Id
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "team_korean_name")
    private String teamKoreanName;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
}
