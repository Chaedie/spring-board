package com.example.springbootboard.domain.users.dto;

import com.example.springbootboard.domain.teams.Team;
import com.example.springbootboard.domain.users.User;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserResponseDTO {

    private String username;
    private String userEmail;
    private Team team;

    @Builder
    public UserResponseDTO(User user) {
        this.username = user.getUsername();
        this.userEmail = user.getUserEmail();
        this.team = user.getTeam();
    }
}
