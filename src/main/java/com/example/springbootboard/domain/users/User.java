package com.example.springbootboard.domain.users;

import com.example.springbootboard.common.BaseTimeEntity;
import com.example.springbootboard.domain.teams.Team;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "users")
@Entity
public class User extends BaseTimeEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", nullable = false, length = 30, unique = true)
    private String username;

    @Column(name = "email", nullable = false, length = 50)
    private String userEmail;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Embedded
    Address homeAddress;

    @Embedded
    Account userAccount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
