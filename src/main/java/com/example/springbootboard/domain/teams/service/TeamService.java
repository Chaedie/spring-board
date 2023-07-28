package com.example.springbootboard.domain.teams.service;

import com.example.springbootboard.domain.teams.dto.TeamRequestDTO;
import com.example.springbootboard.domain.teams.dto.TeamResponseDTO;

import java.util.List;

public interface TeamService {

    void insert(TeamRequestDTO teamRequestDTO);

    List<TeamResponseDTO> getTeamList();

    void deleteById(Long teamId);
}
