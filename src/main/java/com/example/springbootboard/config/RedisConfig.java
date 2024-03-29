package com.example.springbootboard.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    /**
     * 내장 혹은 외부의 Redis를 연결
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.registerModules(new JavaTimeModule(), new Jdk8Module());
        return mapper;
    }

    /**
     * RedisConnection 에서 넘겨준 byte 값 객체 직렬화
     * 1. GenericJackson2JsonRedisSerializer() 의 경우 JSON으로 저장되지만 classPath까지 저장된다.
     * 2. Jackson2JsonRedisSerializer()를 사용할 경우 JOSN형태로만 저장된다.
     * 3. StringRedisSerializer 단순히 string으로 직렬화 역직렬화만한다.
     * <p>
     * 예시)
     * 5) "{\"@class\":\"com.example.springbootboard.domain.teams.dto.TeamResponseDTO\",\"teamName\":\"other\",\"teamKoreanName\":\"\xeb\x8b\xa4\xeb\xa5\xb8\xed\x8c\x80\"}"
     * 6) "{\"teamName\":\"redis\",\"teamKoreanName\":\"\xed\x8c\x80\xeb\xa0\x88\xeb\x94\x94\xec\x8a\xa4\"}"
     * <p>
     */
    @Bean
    public RedisTemplate<?, ?> redisTemplate(ObjectMapper objectMapper) {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        redisTemplate.afterPropertiesSet();
        redisTemplate.setEnableTransactionSupport(true); // 트랜잭션 관리를 위해  필요
        return redisTemplate;
    }

    /**
     * Redis Cache 설정
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()));

        return RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }
}
