package com.example.springbootboard.domain.teams;

import com.example.springbootboard.common.CommonResponse;
import com.example.springbootboard.domain.teams.dto.TeamRequestDTO;
import com.example.springbootboard.domain.teams.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public CommonResponse<Object> insertTeamName(@Validated TeamRequestDTO teamRequestDTO) {
        teamService.insert(teamRequestDTO);

        return CommonResponse.success(teamRequestDTO);
    }


    @DeleteMapping
    public CommonResponse<Object> deleteTeamById(Long teamId) {
        teamService.deleteById(teamId);
        return CommonResponse.success(teamId);
    }
}
