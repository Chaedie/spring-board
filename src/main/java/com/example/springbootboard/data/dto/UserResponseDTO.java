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
public class UserResponseDTO {

    private String userEmail;

    private String nickname;

    private Team team;
}
