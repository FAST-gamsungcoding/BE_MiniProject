package com.gamsung.backend.global.security.jwt;

import com.gamsung.backend.global.jwt.JwtProvider;
import com.gamsung.backend.global.jwt.dto.JwtPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtProvider jwtProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String accessToken = (String) authentication.getCredentials();
        JwtPayload jwtPayload = jwtProvider.verifyToken(accessToken);
        return JwtAuthenticationToken.authenticated(jwtPayload.getEmail());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}