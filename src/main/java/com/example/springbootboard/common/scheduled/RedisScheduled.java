package com.example.springbootboard.common.scheduled;

import com.example.springbootboard.domain.posts.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisScheduled {
    private final PostService postService;

    @Transactional
    @Scheduled(cron = "0 0 * * * ?") // 매 시간마다
    public void syncViewCountWithDB() {
        log.info("현재 시각은 : {}", DateTime.now());
        postService.syncViewCount();
    }
}
