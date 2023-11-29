package com.gamsung.backend.global.jwt.controller;

import com.gamsung.backend.global.common.ApiResponse;
import com.gamsung.backend.global.jwt.JwtPair;
import com.gamsung.backend.global.jwt.controller.request.RefreshAccessTokenRequest;
import com.gamsung.backend.global.jwt.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Tag(name = "JWT 액세스 토큰 재발급")
@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/refresh")
    @Operation(summary = "access 토큰 재발급 API", description = "로그인한 사용자는 자신의 JWT 액세스 토큰이 만료되면 요청하여 액세스 토큰을 재발급 받습니다."
    ,security = @SecurityRequirement(name = "bearer-jwt"))
    public ResponseEntity<ApiResponse<JwtPair>> refreshAccessToken(
            @Valid @RequestBody RefreshAccessTokenRequest request
    ) {
        JwtPair jwtPair = jwtService.refreshAccessToken(request);
        return ResponseEntity.ok(ApiResponse.create(1008, jwtPair));
    }
}
