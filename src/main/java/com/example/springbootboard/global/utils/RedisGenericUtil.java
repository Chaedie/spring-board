package com.example.springbootboard.global.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisGenericUtil<T> {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    // key를 통해 value 리턴
    public T getData(String key, Class<T> type) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String value = valueOperations.get(key);
        if (value == null) {
            return null;
        }
        return getDTOFromJson(value, type);
    }

    public void setData(String key, T value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, getJsonFromDTO(value));
    }

    // 유효 시간 동안 (key, value) 저장
    public void setDataExpire(String key, T value, long durationSeconds) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(durationSeconds);
        valueOperations.set(key, getJsonFromDTO(value), expireDuration);
    }

    // 키값이 없을 경우에만 저장
    public boolean setDataIfAbsent(String key, T value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.setIfAbsent(key, getJsonFromDTO(value));
    }

    public boolean setDataIfAbsentExpire(String key, T value, long durationSeconds) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(durationSeconds);
        return valueOperations.setIfAbsent(key, getJsonFromDTO(value), expireDuration);
    }

    // 삭제
    public void deleteData(String key) {
        redisTemplate.delete(key);
    }

    private String getJsonFromDTO(T t) {
        String result = "";
        try {
            result = objectMapper.writeValueAsString(t);
        } catch (Exception e) {
            log.error(e.toString());
        }
        return result;
    }

    private T getDTOFromJson(String json, Class<T> type) {
        T t = null;
        try {
            t = objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            log.error(e.toString());
        }
        return t;
    }
}
