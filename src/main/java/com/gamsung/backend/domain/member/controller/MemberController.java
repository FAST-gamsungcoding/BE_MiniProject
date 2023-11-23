package com.gamsung.backend.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberController {

    @PostMapping("/login")
    public ResponseEntity<String> memberLogin() {
        return ResponseEntity.ok(
                """
                                {
                                	"code" : 1000,
                                	"data" : {
                                	  "accessToken" : "access-token값",
                                	  "refreshToken" : "refresh-token값"
                                	}
                                }
                        """);
    }


    @PostMapping("/register")
    public ResponseEntity<String> memberRegister() {
        return ResponseEntity.ok(
                """
                                {
                                	"code" : 1003,
                                	"data" : {
                                	  "message" : "회원가입에 성공했습니다."
                                	}
                                }
                        """);
    }


    @GetMapping("/register/check")
    public ResponseEntity<String> memberRegisterEmailCheck() {
        return ResponseEntity.ok(
                """
                                {
                                	"code" : 1006,
                                	"data" : {
                                	  "message" : "회원가입이 가능한 이메일입니다."
                                	}
                                }
                        """);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> memberLogout() {
        return ResponseEntity.ok(
                """
                                {
                                	"code" : 1011,
                                	"data" : {
                                	  "message" : "로그아웃에 성공했습니다."
                                	}
                                }
                        """);
    }
}
