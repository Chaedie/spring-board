package com.example.springbootboard.controller.Rest.v1;

import com.example.springbootboard.common.CommonResponse;
import com.example.springbootboard.domain.emailauth.EmailAuth;
import com.example.springbootboard.domain.emailauth.EmailAuthRepository;
import com.example.springbootboard.domain.posts.Post;
import com.example.springbootboard.domain.posts.PostRepository;
import com.example.springbootboard.domain.teams.Team;
import com.example.springbootboard.domain.teams.TeamRepository;
import com.example.springbootboard.domain.teams.dto.TeamRequestDTO;
import com.example.springbootboard.domain.teams.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dummy")
@RequiredArgsConstructor
public class DummyDataController {

    private final EmailAuthRepository emailAuthRepository;
    private final PostRepository postRepository;
    private final TeamRepository teamRepository;
    private final TeamService teamService;

    @PostMapping("/authMails/insert")
    @Transactional
    public String insert_10_000_AuthMail() {
        for (int i = 0; i < 10_000; i++) {
            emailAuthRepository.save(EmailAuth.builder()
                    .userEmail(UUID.randomUUID().toString())
                    .authCode(UUID.randomUUID().toString().substring(0, 8)).build());
        }
        return "done!";
    }

    @PostMapping("/posts/insert")
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

    @PostMapping("/teams/insert")
    @Transactional
    public String insert_3_default_teams() {

        teamService.insert(TeamRequestDTO.builder().teamName("all").teamKoreanName("전체").build());
        teamService.insert(TeamRequestDTO.builder().teamName("corebanking").teamKoreanName("코어뱅킹").build());
        teamService.insert(TeamRequestDTO.builder().teamName("devops").teamKoreanName("데브옵스").build());

        return "done!";
    }

    @GetMapping("/rent-loan-rate-info")
    public CommonResponse<Object> getRentLoanRateInfo(
            @RequestParam String serviceKey,
            // @RequestParam String pageNo,
            // @RequestParam String numOfRows,
            // @RequestParam String dataType
    ) {
        String pageNo = "1";
        String numOfRows = "10";
        String dataType = "json";
        String block = WebClient.builder()
                .baseUrl("http://apis.data.go.kr").build()
                .get()
                .uri(builder ->
                        builder.path("/B551408/rent-loan-rate-info/rate-list")
                                .queryParam("serviceKey", serviceKey)
                                .queryParam("pageNo", pageNo)
                                .queryParam("numOfRows", numOfRows)
                                .queryParam("dataType", dataType)
                                .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println("block = " + block);
        return CommonResponse.success(block);
    }
}
