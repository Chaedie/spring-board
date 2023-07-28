package com.example.springbootboard.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisMapperUtil {

    private final ObjectMapper objectMapper;

    public <T> String getJsonFromDTO(T t) {
        String result = "";
        try {
            result = objectMapper.writeValueAsString(t);
        } catch (Exception e) {
            log.error(e.toString());
        }
        return result;
    }

    public <T> T getDTOFromJson(String json, Class<T> type) {
        T t = null;
        try {
            t = objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            log.error(e.toString());
        }
        return t;
    }
}
