package com.gamsung.backend.domain.member.exception;

public class MemberLoginWrongPasswordException extends IllegalArgumentException {
    public MemberLoginWrongPasswordException() {
        super("비밀번호가 올바르지 않습니다.");
    }
}
