package com.gamsung.backend.global.jwt.service;

import com.gamsung.backend.global.jwt.JwtProvider;
import com.gamsung.backend.global.jwt.controller.request.RefreshAccessTokenRequest;
import com.gamsung.backend.global.jwt.dto.JwtPair;
import com.gamsung.backend.global.jwt.dto.JwtPayload;
import com.gamsung.backend.global.jwt.entity.JwtBlackListRedisEntity;
import com.gamsung.backend.global.jwt.entity.JwtRedisEntity;
import com.gamsung.backend.global.jwt.exception.JwtExpiredRefreshTokenException;
import com.gamsung.backend.global.jwt.exception.JwtInvalidAccessTokenException;
import com.gamsung.backend.global.jwt.exception.JwtInvalidRefreshTokenException;
import com.gamsung.backend.global.jwt.repository.JwtBlackListRedisRepository;
import com.gamsung.backend.global.jwt.repository.JwtRefreshTokenRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtRefreshTokenRedisRepository jwtRefreshTokenRedisRepository;
    private final JwtBlackListRedisRepository jwtBlackListRedisRepository;
    private final JwtProvider jwtProvider;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    @Value("${service.jwt.refresh-expiration}")
    private Long refreshExpiration;

    public JwtPair createTokenPair(JwtPayload jwtPayload) {
        String accessToken = jwtProvider.createToken(jwtPayload, accessExpiration);
        String refreshToken = jwtProvider.createToken(jwtPayload, refreshExpiration);

        jwtRefreshTokenRedisRepository.save(JwtRedisEntity.builder()
                .memberEmail(jwtPayload.email())
                .refreshToken(refreshToken)
                .expiration(refreshExpiration)
                .build());

        return JwtPair.from(accessToken, refreshToken);
    }

    public JwtPayload verifyAccessToken(String jwtAccessToken) {
        JwtPayload jwtPayload = jwtProvider.getExpiredTokenPayload(jwtAccessToken);
        if (jwtPayload == null) {
            jwtPayload = jwtProvider.verifyAccessToken(jwtAccessToken);
        }
        if (jwtBlackListRedisRepository.findByKey(jwtAccessToken).isPresent()) {
            throw new JwtInvalidAccessTokenException();
        }
        return jwtPayload;
    }

    public JwtPayload verifyRefreshToken(String jwtToken) {
        return jwtProvider.verifyRefreshToken(jwtToken);
    }

    public JwtPair refreshAccessToken(RefreshAccessTokenRequest request) {
        JwtPayload jwtPayload = verifyRefreshToken(request.refreshToken());

        jwtRefreshTokenRedisRepository.findByKey(jwtPayload.email())
                .ifPresentOrElse(refreshToken -> {
                    if (!request.refreshToken().equals(refreshToken)) {
                        throw new JwtInvalidRefreshTokenException();
                    }
                }, () -> {
                    throw new JwtExpiredRefreshTokenException();
                });

        JwtPayload newJwtPayload = JwtPayload.from(Long.parseLong(jwtPayload.id()), jwtPayload.email());

        String newAccessToken = jwtProvider.createToken(newJwtPayload, accessExpiration);

        return JwtPair.from(newAccessToken, request.refreshToken());
    }

    public void deleteRefreshTokenAndAddAccessTokenToBlackList(String email, String accessToken) {
        long refreshTokenExpiredTime = jwtRefreshTokenRedisRepository.getExpire(email);

        jwtRefreshTokenRedisRepository.deleteByKey(email);
        jwtBlackListRedisRepository.save(JwtBlackListRedisEntity.builder()
                .accessToken(accessToken)
                .status("black")
                .expiration(refreshTokenExpiredTime)
                .build()
        );
    }
}
