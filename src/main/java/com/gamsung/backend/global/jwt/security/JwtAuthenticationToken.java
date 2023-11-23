package com.gamsung.backend.global.jwt.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String principal;
    private String credentials;

    private JwtAuthenticationToken(String principal, String credentials, boolean isAuthenticated) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(isAuthenticated);
    }

    private JwtAuthenticationToken(String principal, boolean isAuthenticated) {
        super(AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.principal = principal;
        setAuthenticated(isAuthenticated);
    }

    public static JwtAuthenticationToken unauthenticated(String accessToken) {
        return new JwtAuthenticationToken(null, accessToken, false);
    }

    public static JwtAuthenticationToken authenticated(String email) {
        return new JwtAuthenticationToken(email, true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
