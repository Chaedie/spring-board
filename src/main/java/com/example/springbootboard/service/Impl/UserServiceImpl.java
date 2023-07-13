package com.example.springbootboard.service.Impl;

import com.example.springbootboard.data.dto.UserRequestDTO;
import com.example.springbootboard.data.dto.UserResponseDTO;
import com.example.springbootboard.data.entity.Team;
import com.example.springbootboard.data.repository.TeamRepository;
import com.example.springbootboard.data.repository.UserRepository;
import com.example.springbootboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final TeamRepository teamRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }
    @Override
    public List<String> getTeamNameList() {
        List<Team> teamList = teamRepository.findAll();
        List<String> teamNameList = new ArrayList<>();
        for (Team team : teamList) {
            teamNameList.add(team.getTeamName());
        }

        return teamNameList;
    }
}
