package com.example.springbootboard.service;

import com.example.springbootboard.data.dto.TeamRequestDTO;
import com.example.springbootboard.data.dto.TeamResponseDTO;

import java.util.List;

public interface TeamService {

    void insert(TeamRequestDTO teamRequestDTO);

    List<TeamResponseDTO> getTeamList();
}
