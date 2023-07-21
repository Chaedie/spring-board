package com.example.springbootboard.service.Impl;

import com.example.springbootboard.data.dto.TeamRequestDTO;
import com.example.springbootboard.data.dto.TeamResponseDTO;
import com.example.springbootboard.data.entity.Team;
import com.example.springbootboard.data.repository.TeamRepository;
import com.example.springbootboard.global.error.Exception.ItemNotFoundException;
import com.example.springbootboard.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final RedisTemplate<String, TeamResponseDTO> redisTemplate;

    @Override
    @Transactional
    public void insert(TeamRequestDTO teamRequestDTO) {
        Team team = teamRepository.save(teamRequestDTO.toEntity());
        TeamResponseDTO teamResponseDTO = new TeamResponseDTO(team);

        redisTemplate.opsForList().rightPush("teamList", teamResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamResponseDTO> getTeamList() {
        List<Team> teamList = teamRepository.findAll();
        List<TeamResponseDTO> teamResponseDTOList = new ArrayList<>();
        teamList.forEach(team -> teamResponseDTOList.add(new TeamResponseDTO(team)));

        return teamResponseDTOList;
    }

    @Override
    public void deleteById(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(ItemNotFoundException::new);
        teamRepository.delete(team);

        redisTemplate.opsForList().remove("teamList", 1, new TeamResponseDTO(team));
    }
}
