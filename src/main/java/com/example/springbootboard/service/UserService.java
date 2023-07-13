package com.example.springbootboard.service;

import com.example.springbootboard.data.dto.UserRequestDTO;
import com.example.springbootboard.data.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<String> getTeamNameList();
}
