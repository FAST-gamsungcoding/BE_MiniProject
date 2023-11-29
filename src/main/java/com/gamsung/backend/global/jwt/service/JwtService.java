package com.gamsung.backend.global.jwt.service;

import com.gamsung.backend.global.jwt.JwtPair;
import com.gamsung.backend.global.jwt.JwtProvider;
import com.gamsung.backend.global.jwt.controller.request.RefreshAccessTokenRequest;
import com.gamsung.backend.global.jwt.dto.JwtPayload;
import com.gamsung.backend.global.jwt.entity.JwtBlackListRedisEntity;
import com.gamsung.backend.global.jwt.entity.JwtRedisEntity;
import com.gamsung.backend.global.jwt.exception.JwtExpiredRefreshTokenException;
import com.gamsung.backend.global.jwt.exception.JwtInvalidAccessTokenException;
import com.gamsung.backend.global.jwt.exception.JwtInvalidRefreshTokenException;
import com.gamsung.backend.global.jwt.repository.JwtBlackListRedisRepository;
import com.gamsung.backend.global.jwt.repository.JwtRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtRedisRepository jwtRedisRepository;
    private final JwtBlackListRedisRepository jwtBlackListRedisRepository;
    private final JwtProvider jwtProvider;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    @Value("${service.jwt.refresh-expiration}")
    private Long refreshExpiration;

    public JwtPair createTokenPair(JwtPayload jwtPayload) {
        String accessToken = jwtProvider.createToken(jwtPayload, accessExpiration);
        String refreshToken = jwtProvider.createToken(jwtPayload, refreshExpiration);

        jwtRedisRepository.save(JwtRedisEntity.builder()
                .memberEmail(jwtPayload.getEmail())
                .refreshToken(refreshToken)
                .expiration(refreshExpiration)
                .build());

        return JwtPair.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public JwtPayload verifyToken(String jwtToken) {
        JwtPayload jwtPayload = jwtProvider.verifyToken(jwtToken);
        if (jwtBlackListRedisRepository.findByKey(jwtToken).isPresent()) {
            throw new JwtInvalidAccessTokenException();
        }
        return jwtPayload;
    }

    public JwtPair refreshAccessToken(RefreshAccessTokenRequest request) {
        JwtPayload jwtPayload = verifyToken(request.refreshToken());

        jwtRedisRepository.findByKey(jwtPayload.getEmail())
                .ifPresentOrElse(refreshToken -> {
                    if (!request.refreshToken().equals(refreshToken)) {
                        throw new JwtInvalidRefreshTokenException();
                    }
                }, () -> {
                    throw new JwtExpiredRefreshTokenException();
                });

        JwtPayload newJwtPayload = JwtPayload.from(Long.parseLong(jwtPayload.getId()), jwtPayload.getEmail());

        String newAccessToken = jwtProvider.createToken(newJwtPayload, accessExpiration);

        return JwtPair.builder()
                .accessToken(newAccessToken)
                .refreshToken(request.refreshToken())
                .build();
    }

    public void deleteRefreshTokenAndAddAccessTokenToBlackList(String email, String accessToken) {
        long refreshTokenExpiredTime = jwtRedisRepository.getExpire(email);

        jwtRedisRepository.deleteByKey(email);
        jwtBlackListRedisRepository.save(JwtBlackListRedisEntity.builder()
                .accessToken(accessToken)
                .status("black")
                .expiration(refreshTokenExpiredTime)
                .build()
        );
    }
}
