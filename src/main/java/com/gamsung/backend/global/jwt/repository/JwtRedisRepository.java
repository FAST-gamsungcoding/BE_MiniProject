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

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(JwtRedisEntity entity) {
        redisTemplate.opsForValue()
                .set(entity.getMemberEmail(), entity.getRefreshToken(),
                        entity.getExpiration(), TimeUnit.MILLISECONDS);
    }

    @Override
    public Optional<String> findByKey(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public Boolean deleteByKey(String key) {
        return redisTemplate.delete(key);
    }
}
