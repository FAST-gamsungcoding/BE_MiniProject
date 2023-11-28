package com.gamsung.backend.global.jwt.repository;

import com.gamsung.backend.global.common.BaseRedisContainerTest;
import com.gamsung.backend.global.jwt.JwtProvider;
import com.gamsung.backend.global.jwt.dto.JwtPayload;
import com.gamsung.backend.global.jwt.entity.JwtRedisEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class JwtRedisRepositoryTest extends BaseRedisContainerTest {

    @Autowired
    private JwtRedisRepository jwtRedisRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @DisplayName("Redis에 저장된 값의 만료 시간이 종료되면 값을 찾을 수 없다.")
    @Test
    public void notFoundRedisKeyWhenExpiredKeyTest() throws InterruptedException {
        // given
        Long id = 1234L;
        String email = "test@test.com";
        JwtPayload jwtPayload = JwtPayload.from(id, email);
        JwtRedisEntity jwtRedisEntity = JwtRedisEntity.builder()
                .memberEmail(email)
                .refreshToken(jwtProvider.createToken(jwtPayload, 1))
                .expiration(1)
                .build();

        // when
        jwtRedisRepository.save(jwtRedisEntity);

        // then
        Thread.sleep(100);
        Assertions.assertEquals(-2, jwtRedisRepository.getExpire(email));
    }

    @DisplayName("Redis에 저장된 값을 찾아서 삭제할 수 있다.")
    @Test
    public void deleteKeyWhenFindingSavedValueTest() {
        // given
        Long id = 1234L;
        String email = "test2@test.com";
        JwtPayload jwtPayload = JwtPayload.from(id, email);
        JwtRedisEntity jwtRedisEntity = JwtRedisEntity.builder()
                .memberEmail(email)
                .refreshToken(jwtProvider.createToken(jwtPayload, 10000))
                .expiration(10000)
                .build();
        jwtRedisRepository.save(jwtRedisEntity);

        // when
        jwtRedisRepository.deleteByKey(email);

        // then
        Assertions.assertTrue(jwtRedisRepository.findByKey(email).isEmpty());
    }
}