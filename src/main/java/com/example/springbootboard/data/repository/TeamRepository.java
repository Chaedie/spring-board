package com.example.springbootboard.data.repository;

import com.example.springbootboard.data.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
