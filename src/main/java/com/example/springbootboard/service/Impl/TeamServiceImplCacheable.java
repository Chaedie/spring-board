package com.example.springbootboard.service.Impl;

import com.example.springbootboard.data.dto.TeamRequestDTO;
import com.example.springbootboard.data.dto.TeamResponseDTO;
import com.example.springbootboard.data.entity.Team;
import com.example.springbootboard.data.repository.TeamRepository;
import com.example.springbootboard.global.error.Exception.ItemNotFoundException;
import com.example.springbootboard.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Cacheable 을 활용해 캐시매니져가 알아서 <String, String>으로 캐싱되도록 했습니다.
 * insert, delete과 같이 데이터 변화가 생길땐 cache 삭제만 하여 cache miss를 유도했습니다.
 * But, 팀 정보의 경우 insert, delete가 빈번하지 않은 반면 Select는 빈번합니다.
 * 따라서 해당 정책은 좋지 못하다고 판단하여 훨씬 복잡도는 높지만 Redis List 자료구조를 활용하여 구현했습니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TeamServiceImplCacheable implements TeamService {

    private final TeamRepository teamRepository;
    private final RedisTemplate<String, String> redisTemplate;

    private final String TEAM_LIST = "teamList";
    private final String REDIS_KEY_TEAM_LIST = "teamList::SimpleKey []";


    @Override
    @Transactional
    public void insert(TeamRequestDTO teamRequestDTO) {
        Team team = teamRepository.save(teamRequestDTO.toEntity());

        redisTemplate.delete(REDIS_KEY_TEAM_LIST);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(TEAM_LIST) // Cacheable의 경우 list를 string으로 직렬화해서 저장한다.
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

        redisTemplate.delete(REDIS_KEY_TEAM_LIST);
    }
}
