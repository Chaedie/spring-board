package com.example.springbootboard.domain.teams;

import com.example.springbootboard.common.ResponseDTO;
import com.example.springbootboard.domain.teams.dto.TeamRequestDTO;
import com.example.springbootboard.domain.teams.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseDTO<TeamRequestDTO> insertTeamName(@Validated TeamRequestDTO teamRequestDTO) {
        teamService.insert(teamRequestDTO);

        return ResponseDTO.of(200, "팀 생성 성공", teamRequestDTO);
    }

    @DeleteMapping
    public ResponseDTO<String> deleteTeamById(Long teamId) {
        teamService.deleteById(teamId);
        return ResponseDTO.of(204, "삭제 성공", "");
    }
}
