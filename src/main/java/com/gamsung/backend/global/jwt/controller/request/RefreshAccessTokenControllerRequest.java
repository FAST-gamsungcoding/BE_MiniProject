package com.gamsung.backend.global.jwt.controller.request;

import jakarta.validation.Valid;

public record RefreshAccessTokenControllerRequest(
        @Valid
        RefreshAccessTokenRequest data
){
}
