package com.gamsung.backend.global.jwt.repository;

import com.gamsung.backend.global.jwt.entity.JwtRedisEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Repository
public class JwtRedisRepository implements RedisRepository<JwtRedisEntity> {

    private final static String JWT_KEY_PREFIX = "jwt:refresh:";

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(JwtRedisEntity entity) {
        String key = JWT_KEY_PREFIX + entity.getMemberEmail();
        String value = entity.getRefreshToken();
        long expire = entity.getExpiration();
        if (redisTemplate.opsForValue().get(key) != null) {
            redisTemplate.delete(key);
        }
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.MILLISECONDS);
    }

    @Override
    public Optional<String> findByKey(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(JWT_KEY_PREFIX + key));
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(JWT_KEY_PREFIX + key);
    }

    @Override
    public Boolean deleteByKey(String key) {
        return redisTemplate.delete(JWT_KEY_PREFIX + key);
    }
}
