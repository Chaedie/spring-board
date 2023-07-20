package com.example.springbootboard.global.scheduled;

import com.example.springbootboard.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisScheduled {
    private final PostService postService;

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?") // 매일 0시에
    public void syncViewCountWithDB() {
        postService.syncViewCount();
    }
}
