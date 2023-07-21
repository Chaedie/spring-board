package com.example.springbootboard.data.dto;

import com.example.springbootboard.data.entity.Team;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class TeamRequestDTO {

    private String teamName;
    private String teamKoreanName;

    public Team toEntity() {
        return Team.builder()
                .teamName(teamName)
                .teamKoreanName(teamKoreanName)
                .build();
    }
}
