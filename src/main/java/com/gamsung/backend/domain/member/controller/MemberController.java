package com.gamsung.backend.domain.member.controller;

import com.gamsung.backend.domain.member.controller.request.MemberControllerLoginRequest;
import com.gamsung.backend.domain.member.controller.request.MemberControllerRegisterEmailCheckRequest;
import com.gamsung.backend.domain.member.controller.request.MemberControllerRegisterRequest;
import com.gamsung.backend.domain.member.dto.request.MemberLoginRequest;
import com.gamsung.backend.domain.member.dto.request.MemberRegisterRequest;
import com.gamsung.backend.domain.member.service.MemberService;
import com.gamsung.backend.global.common.ControllerResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.gamsung.backend.global.config.SwaggerDescriptionConfig.*;

@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    @Operation(summary = "로그인 API", description = MEMBER_LOGIN)
    public ResponseEntity<ControllerResponse> memberLogin(
            @Valid @RequestBody MemberControllerLoginRequest loginRequest
    ) {
        /**
         * 테스트 데이터가 아닌 경우 이 응답 방식을 사용하면 됩니다.
         return ResponseEntity.ok(
         ApiResponse.builder()
         .code(HttpStatus.OK.value())
         .data(tripService.getAllTripsPaging(pageable))
         .build()
         );
         **/
        return ResponseEntity.ok(ControllerResponse.builder()
                .code(1000)
                .data(memberService.login(MemberLoginRequest.from(loginRequest)))
                .build());
    }

    @PostMapping("/register")
    @Operation(summary = "회원가입 API", description = MEMBER_RIGISTER)
    public ResponseEntity<ControllerResponse> memberRegister(
            @Valid @RequestBody MemberControllerRegisterRequest registerRequest
    ) {
        return ResponseEntity.created(URI.create("/")).body(ControllerResponse.builder()
                .code(1003)
                .data(memberService.register(MemberRegisterRequest.from(registerRequest)))
                .build());
    }

    @GetMapping("/register/check")
    @Operation(summary = "이메일 중복체크 API", description = MEMBER_REGISTER_EMAIL_CHECK)
    public ResponseEntity<ControllerResponse> memberRegisterEmailCheck(
            @Valid MemberControllerRegisterEmailCheckRequest emailCheckRequest
    ) {
        return ResponseEntity.ok(ControllerResponse.builder()
                .code(1006)
                .data(memberService.emailCheck(emailCheckRequest.email()))
                .build());
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃 API", description = MEMBER_LOGOUT)
    public ResponseEntity<ControllerResponse> memberLogout() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(ControllerResponse.builder()
                .code(1011)
                .data(memberService.logout(email))
                .build());
    }
}
