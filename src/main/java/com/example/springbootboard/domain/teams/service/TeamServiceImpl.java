package com.example.springbootboard.domain.teams.service;

import com.example.springbootboard.common.error.Exception.ItemNotFoundException;
import com.example.springbootboard.domain.teams.Team;
import com.example.springbootboard.domain.teams.TeamRepository;
import com.example.springbootboard.domain.teams.dto.TeamRequestDTO;
import com.example.springbootboard.domain.teams.dto.TeamResponseDTO;
import com.example.springbootboard.utils.RedisMapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Redis의 list 자료구조를 활용하여 직접 구현한 Service
 * insert, delete 시 List 데이터 전체를 썻다 지웠다 하는것보단 rightPush, remove 작업만 하도록 하였습니다.
 * Cache miss 발생 시 DTO 전환과정이 있다.
 * 성능 측정 시 유의미한 결과는 발견하지 못했다.
 */
@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final RedisMapperUtil redisMapperUtil;

    private final String TEAM_LIST = "teamList";

    @Override
    @Transactional
    public void insert(TeamRequestDTO teamRequestDTO) {
        Team team = teamRepository.save(teamRequestDTO.toEntity());

        redisTemplate.opsForList().rightPush(TEAM_LIST, redisMapperUtil.getJsonFromDTO(new TeamResponseDTO(team)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamResponseDTO> getTeamList() {

        List<String> serializedList = redisTemplate.opsForList().range(TEAM_LIST, 0, -1);
        if (serializedList.size() == 0) {
            List<Team> teamList = teamRepository.findAll();
            List<TeamResponseDTO> teamResponseDTOList = new ArrayList<>();
            teamList.forEach(team -> teamResponseDTOList.add(new TeamResponseDTO(team)));
            teamResponseDTOList
                    .forEach(team -> redisTemplate.opsForList().rightPush(TEAM_LIST, redisMapperUtil.getJsonFromDTO(team)));

            return teamResponseDTOList;
        }

        List<TeamResponseDTO> teamResponseDTOList = serializedList
                .stream()
                .map(team -> redisMapperUtil.getDTOFromJson(team, TeamResponseDTO.class))
                .collect(Collectors.toList());

        return teamResponseDTOList;
    }

    @Override
    public void deleteById(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(ItemNotFoundException::new);
        teamRepository.delete(team);

        redisTemplate.opsForList().remove(TEAM_LIST, 1, redisMapperUtil.getJsonFromDTO(new TeamResponseDTO(team)));
    }
}
