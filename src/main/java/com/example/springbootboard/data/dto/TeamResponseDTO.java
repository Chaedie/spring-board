package com.example.springbootboard.data.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class TeamResponseDTO {

    private List<String> teamNameList;
    
}
