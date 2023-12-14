package com.gamsung.backend.global.jwt;

import com.gamsung.backend.global.jwt.dto.JwtPayload;
import com.gamsung.backend.global.jwt.exception.JwtExpiredAccessTokenException;
import com.gamsung.backend.global.jwt.exception.JwtExpiredRefreshTokenException;
import com.gamsung.backend.global.jwt.exception.JwtInvalidAccessTokenException;
import com.gamsung.backend.global.jwt.exception.JwtInvalidRefreshTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {
    private static final String USER_ID_KEY = "client_id";
    private static final String USER_EMAIL_KEY = "email";

    @Value("${spring.application.name}")
    private String issuer;

    private final SecretKey secretKey;

    public JwtProvider(@Value("${service.jwt.secret-key}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String createToken(JwtPayload jwtPayload, long expiration) {
        return Jwts.builder()
                .claim(USER_ID_KEY, jwtPayload.id())
                .claim(USER_EMAIL_KEY, jwtPayload.email())
                .issuer(issuer)
                .issuedAt(jwtPayload.issuedAt())
                .expiration(new Date(jwtPayload.issuedAt().getTime() + expiration))
                .signWith(secretKey)
                .compact();
    }

    private JwtPayload verifyToken(String jwtToken) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
        return JwtPayload.from(
                claims.get(USER_ID_KEY, String.class),
                claims.get(USER_EMAIL_KEY, String.class),
                claims.getIssuedAt()
        );
    }

    public JwtPayload verifyAccessToken(String jwtToken) {
        try {
            return verifyToken(jwtToken);
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredAccessTokenException();
        } catch (IllegalArgumentException | SignatureException | MalformedJwtException e) {
            throw new JwtInvalidAccessTokenException();
        }
    }

    public JwtPayload verifyRefreshToken(String jwtToken) {
        try {
            return verifyToken(jwtToken);
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredRefreshTokenException();
        } catch (IllegalArgumentException | SignatureException | MalformedJwtException e) {
            throw new JwtInvalidRefreshTokenException();
        }
    }

    public JwtPayload getExpiredTokenPayload(String jwtToken) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(jwtToken)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            return JwtPayload.from(
                    e.getClaims().get(USER_ID_KEY, String.class),
                    e.getClaims().get(USER_EMAIL_KEY, String.class),
                    e.getClaims().getIssuedAt());
        } catch (IllegalArgumentException | SignatureException | MalformedJwtException e) {
            throw new JwtInvalidAccessTokenException();
        }
        return null;
    }
}
