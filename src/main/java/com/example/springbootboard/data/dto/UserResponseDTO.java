package com.example.springbootboard.data.dto;

import com.example.springbootboard.data.entity.Team;
import com.example.springbootboard.data.entity.User;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UserResponseDTO {

    private String username;
    private String userEmail;
    private Team team;

    public UserResponseDTO(User user) {
        this.username = user.getUsername();
        this.userEmail = user.getUserEmail();
        this.team = user.getTeam();
    }
}
