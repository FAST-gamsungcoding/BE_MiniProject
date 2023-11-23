package com.gamsung.backend.domain.member.dto.request;

import com.gamsung.backend.domain.member.controller.request.MemberControllerLoginRequest;

public record MemberLoginRequest(
        String email,
        String password
) {
    public static MemberLoginRequest from(MemberControllerLoginRequest controllerLoginRequest) {
        return new MemberLoginRequest(
                controllerLoginRequest.email(),
                controllerLoginRequest.password()
        );
    }
}
