package com.gamsung.backend.global.jwt;

import com.gamsung.backend.global.jwt.dto.JwtPayload;
import com.gamsung.backend.global.jwt.exception.JwtExpiredAccessTokenException;
import com.gamsung.backend.global.jwt.exception.JwtInvalidAccessTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {
    private static final String USER_EMAIL_KEY = "email";

    @Value("${spring.application.name}")
    private String issuer;

    private final SecretKey secretKey;

    public JwtProvider(@Value("${service.jwt.secret-key}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String createToken(JwtPayload jwtPayload, long expiration) {
        return Jwts.builder()
                .claim(USER_EMAIL_KEY, jwtPayload.getEmail())
                .issuer(issuer)
                .issuedAt(jwtPayload.getIssuedAt())
                .expiration(new Date(jwtPayload.getIssuedAt().getTime() + expiration))
                .signWith(secretKey)
                .compact();
    }

    public JwtPayload verifyToken(String jwtToken) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(jwtToken)
                    .getPayload();
            return JwtPayload.builder()
                    .email(claims.get(USER_EMAIL_KEY, String.class))
                    .issuedAt(claims.getIssuedAt())
                    .build();
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredAccessTokenException();
        } catch (JwtException e) {
            throw new JwtInvalidAccessTokenException();
        }
    }
}
