package com.gamsung.backend.global.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

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
        super.setAuthenticated(isAuthenticated);
    }

    private JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String principal) {
        super(authorities);
        this.principal = principal;
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

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
