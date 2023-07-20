package com.example.springbootboard.global.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisUtil {

    // StringRedisTemplate 은 RedisTemplate<String, String> 를 extends한 Redis의 Build-in Class 
    private final StringRedisTemplate redisTemplate;

    // key를 통해 value 리턴
    public String getData(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void setData(String key, String value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    // 유효 시간 동안 (key, value) 저장
    public void setDataExpire(String key, String value, long durationSeconds) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(durationSeconds);
        valueOperations.set(key, value, expireDuration);
    }

    // 키값이 없을 경우에만 저장
    public boolean setDataIfAbsent(String key, String value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.setIfAbsent(key, value);
    }

    public boolean setDataIfAbsentExpire(String key, String value, long durationSeconds) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(durationSeconds);
        return valueOperations.setIfAbsent(key, value, expireDuration);
    }


    // 삭제
    public void deleteData(String key) {
        redisTemplate.delete(key);
    }
}
