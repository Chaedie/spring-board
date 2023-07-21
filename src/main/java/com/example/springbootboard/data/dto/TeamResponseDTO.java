package com.example.springbootboard.data.dto;

import com.example.springbootboard.data.entity.Team;
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
