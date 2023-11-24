package com.gamsung.backend.global.jwt.controller;

import com.gamsung.backend.global.common.ControllerResponse;
import com.gamsung.backend.global.jwt.controller.request.RefreshAccessTokenRequest;
import com.gamsung.backend.global.jwt.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/refresh")
    public ResponseEntity<ControllerResponse> refreshAccessToken(
            @Valid @RequestBody RefreshAccessTokenRequest request
    ) {
        return ResponseEntity.ok(ControllerResponse.builder()
                .code(1008)
                .data(jwtService.refreshAccessToken(request))
                .build());
    }
}