package com.gamsung.backend.global.config;

import com.gamsung.backend.global.jwt.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityFilterConfig {

	private final JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain http(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http
			.authorizeHttpRequests(request ->
					request
							.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
							.anyRequest().permitAll()
		);

		http.addFilterAfter(jwtFilter, LogoutFilter.class);

		return http.build();
	}
}
