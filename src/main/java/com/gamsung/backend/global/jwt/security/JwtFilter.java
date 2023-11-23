package com.gamsung.backend.global.jwt.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private static final String JWT_TOKEN_PREFIX = "Bearer ";

    private final AuthenticationManager authenticationManager;

    private static String extractAccessToken(String authHeaderValue) {
        if (StringUtils.hasText(authHeaderValue) && authHeaderValue.startsWith(JWT_TOKEN_PREFIX)) {
            return authHeaderValue.substring(JWT_TOKEN_PREFIX.length());
        }
        return "";
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String accessToken = extractAccessToken(request.getHeader(HttpHeaders.AUTHORIZATION));

        if (!accessToken.isEmpty()) {
            JwtAuthenticationToken token = JwtAuthenticationToken.unauthenticated(accessToken);
            token.setDetails(new WebAuthenticationDetails(request));
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
