package com.gamsung.backend.domain.member.exception;

public class MemberAlreadyExistedException extends IllegalArgumentException {
    public MemberAlreadyExistedException() {
        super("이미 존재하는 회원 아이디입니다.");
    }
}
