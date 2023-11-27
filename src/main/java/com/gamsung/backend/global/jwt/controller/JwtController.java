package com.gamsung.backend.global.jwt.controller;

import com.gamsung.backend.global.common.ApiResponse;
import com.gamsung.backend.global.jwt.controller.request.RefreshAccessTokenRequest;
import com.gamsung.backend.global.jwt.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gamsung.backend.global.config.SwaggerDescriptionConfig.ACCESS_TOKEN;


@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/refresh")
    @Operation(summary = "access 토큰 재발급 API", description = ACCESS_TOKEN)
    public ResponseEntity<ApiResponse> refreshAccessToken(
            @Valid @RequestBody RefreshAccessTokenRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.builder()
                .code(1008)
                .data(jwtService.refreshAccessToken(request))
                .build());
    }
}
