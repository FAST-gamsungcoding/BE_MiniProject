package com.gamsung.backend.global.jwt.service;

import com.gamsung.backend.global.common.BaseRedisContainerTest;
import com.gamsung.backend.global.jwt.JwtPair;
import com.gamsung.backend.global.jwt.dto.JwtPayload;
import com.gamsung.backend.global.jwt.repository.JwtRedisRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;

class JwtServiceTest extends BaseRedisContainerTest {
    private static final Long TEST_ID = 1234L;
    private static final String TEST_EMAIL = "test@test.com";

    @Autowired
    private JwtService jwtService;

    @Autowired
    private JwtRedisRepository jwtRedisRepository;

    @DisplayName("TokenPair를 발급할 때")
    @Nested
    class CreateTokenPair {

        @DisplayName("토큰 발급과 리프레시 토큰 저장을 할 수 있다.")
        @Test
        public void successToCreateJwtTokenPair() {
            // given
            Date issuedAt = Date.from(Instant.now());
            JwtPayload jwtPayload = JwtPayload.builder()
                    .id(String.valueOf(TEST_ID))
                    .email(TEST_EMAIL)
                    .issuedAt(issuedAt)
                    .build();

            // when
            JwtPair jwtPair = jwtService.createTokenPair(jwtPayload);
            String storedRefreshToken = jwtRedisRepository.findByKey(TEST_EMAIL)
                    .orElse(null);

            // then

            // jwtPair Check
            JwtPayload verifiedJwtAccessTokenPayload = jwtService.verifyToken(jwtPair.getAccessToken());
            JwtPayload verifiedJwtRefreshTokenPayload = jwtService.verifyToken(jwtPair.getRefreshToken());

            Assertions.assertEquals(TEST_EMAIL, verifiedJwtAccessTokenPayload.getEmail());
            Assertions.assertEquals(TEST_EMAIL, verifiedJwtRefreshTokenPayload.getEmail());

            Assertions.assertEquals(issuedAt.getTime() / 1000, verifiedJwtAccessTokenPayload.getIssuedAt().getTime() / 1000);
            Assertions.assertEquals(issuedAt.getTime() / 1000, verifiedJwtRefreshTokenPayload.getIssuedAt().getTime() / 1000);

            // Redis Save Check
            Assertions.assertEquals(jwtPair.getRefreshToken(), storedRefreshToken);
            Assertions.assertTrue(jwtRedisRepository.getExpire(TEST_EMAIL) > -1);
        }
    }
}