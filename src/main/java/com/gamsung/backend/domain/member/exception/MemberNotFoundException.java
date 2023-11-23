package com.gamsung.backend.domain.member.exception;

public class MemberNotFoundException extends IllegalArgumentException {
    public MemberNotFoundException() {
        super("회원 아이디가 존재하지 않습니다.");
    }
}
