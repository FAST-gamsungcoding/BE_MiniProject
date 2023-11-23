package com.gamsung.backend.domain.member.dto.request;

import com.gamsung.backend.domain.member.controller.request.MemberControllerRegisterRequest;

public record MemberRegisterRequest(
        String email,
        String password,
        String name
) {
    public static MemberRegisterRequest from(MemberControllerRegisterRequest controllerRegisterRequest) {
        return new MemberRegisterRequest(
                controllerRegisterRequest.email(),
                controllerRegisterRequest.password(),
                controllerRegisterRequest.name()
        );
    }
}
