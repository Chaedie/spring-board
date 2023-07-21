package com.example.springbootboard.controller.Rest.v1;

import com.example.springbootboard.data.dto.ResponseDTO;
import com.example.springbootboard.data.dto.TeamRequestDTO;
import com.example.springbootboard.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
        // if (errors.hasErrors()) {
        //     return ResponseDTO.of(400, "입력값을 확인해주세요.", teamRequestDTO);
        // }

        teamService.insert(teamRequestDTO);

        return ResponseDTO.of(200, "팀 생성 성공", teamRequestDTO);
    }
}
