package com.gamsung.backend.domain.member.controller;

import com.gamsung.backend.domain.member.controller.request.MemberControllerLoginRequest;
import com.gamsung.backend.domain.member.controller.request.MemberControllerRegisterEmailCheckRequest;
import com.gamsung.backend.domain.member.controller.request.MemberControllerRegisterRequest;
import com.gamsung.backend.domain.member.dto.request.MemberLoginRequest;
import com.gamsung.backend.domain.member.dto.request.MemberRegisterRequest;
import com.gamsung.backend.domain.member.service.MemberService;
import com.gamsung.backend.global.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> memberLogin(
            @Valid @RequestBody MemberControllerLoginRequest loginRequest
    ) {
        return ResponseEntity.ok(ApiResponse.builder()
                .code(1000)
                .data(memberService.login(MemberLoginRequest.from(loginRequest)))
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> memberRegister(
            @Valid @RequestBody MemberControllerRegisterRequest registerRequest
    ) {
        return ResponseEntity.created(URI.create("/")).body(ApiResponse.builder()
                .code(1003)
                .data(memberService.register(MemberRegisterRequest.from(registerRequest)))
                .build());
    }

    @GetMapping("/register/check")
    public ResponseEntity<ApiResponse> memberRegisterEmailCheck(
            @Valid MemberControllerRegisterEmailCheckRequest emailCheckRequest
    ) {
        return ResponseEntity.ok(ApiResponse.builder()
                .code(1006)
                .data(memberService.emailCheck(emailCheckRequest.email()))
                .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> memberLogout() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(ApiResponse.builder()
                .code(1011)
                .data(memberService.logout(email))
                .build());
    }
}
