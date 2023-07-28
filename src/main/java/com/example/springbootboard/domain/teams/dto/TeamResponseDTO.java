package com.example.springbootboard.domain.teams.dto;

import com.example.springbootboard.domain.teams.Team;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"teamName", "teamKoreanName"})
@Builder
public class TeamResponseDTO {

    private String teamName;
    private String teamKoreanName;

    public TeamResponseDTO(Team team) {
        this.teamName = team.getTeamName();
        this.teamKoreanName = team.getTeamKoreanName();
    }
}
