package com.example.springbootboard.controller.Rest.v1;

import com.example.springbootboard.data.dto.TeamRequestDTO;
import com.example.springbootboard.data.entity.EmailAuth;
import com.example.springbootboard.data.entity.Post;
import com.example.springbootboard.data.entity.Team;
import com.example.springbootboard.data.repository.EmailAuthRepository;
import com.example.springbootboard.data.repository.PostRepository;
import com.example.springbootboard.data.repository.TeamRepository;
import com.example.springbootboard.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/rest/v1/dummy")
@RequiredArgsConstructor
public class DummyDataController {

    private final EmailAuthRepository emailAuthRepository;
    private final PostRepository postRepository;
    private final TeamRepository teamRepository;
    private final TeamService teamService;

    @GetMapping("/authMails/insert")
    @Transactional
    public String insert_10_000_AuthMail() {
        for (int i = 0; i < 10_000; i++) {
            emailAuthRepository.save(EmailAuth.builder()
                    .userEmail(UUID.randomUUID().toString())
                    .authCode(UUID.randomUUID().toString().substring(0, 8)).build());
        }
        return "done!";
    }

    @GetMapping("/posts/insert")
    @Transactional
    public String insert_10_000_posts() {
        Team team = teamRepository.findById(1L).get();
        for (int i = 0; i < 10_000; i++) {
            postRepository.save(
                    Post.builder()
                            .postTitle(UUID.randomUUID().toString())
                            .postContent("디도스 컨텐츠ㅋㅋㅋ")
                            .nickname("SpringBoot")
                            .password("asdfasdf")
                            .userId(1L)
                            .team(team)
                            .build()
            );
        }
        return "done!";
    }

    @GetMapping("/teams/insert")
    @Transactional
    public String insert_3_default_teams() {

        teamService.insert(TeamRequestDTO.builder().teamName("all").teamKoreanName("전체").build());
        teamService.insert(TeamRequestDTO.builder().teamName("corebanking").teamKoreanName("코어뱅킹").build());
        teamService.insert(TeamRequestDTO.builder().teamName("devops").teamKoreanName("데브옵스").build());

        return "done!";
    }
}
