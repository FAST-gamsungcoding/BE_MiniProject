package com.gamsung.backend.global.jwt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamsung.backend.global.exception.ErrorCode;
import com.gamsung.backend.global.jwt.controller.request.RefreshAccessTokenRequest;
import com.gamsung.backend.global.jwt.dto.JwtPair;
import com.gamsung.backend.global.jwt.exception.JwtExpiredRefreshTokenException;
import com.gamsung.backend.global.jwt.exception.JwtInvalidRefreshTokenException;
import com.gamsung.backend.global.jwt.service.JwtService;
import com.gamsung.backend.global.security.WithMockCustomMember;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JwtController.class)
@ActiveProfiles("test")
@MockBean(JpaMetamodelMappingContext.class)
class JwtControllerTest {

    private final static String BASE_URL = "/v1/member";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtService jwtService;

    @DisplayName("JWT 리프레시 토큰 유닛 테스트")
    @Nested
    class RefreshAccessTokenUnitTest {
        @DisplayName("1008 : 토큰 리프레시 성공")
        @WithMockCustomMember
        @Test
        void successToRefreshAccessToken() throws Exception {
            // given
            JwtPair testToken = JwtPair.from("fake-access-token", "fake-refresh-token");
            JwtPair refreshedTestToken = JwtPair.from("fake-refreshed-access-token", "fake-refresh-token");
            RefreshAccessTokenRequest refreshAccessTokenRequest= new RefreshAccessTokenRequest(testToken.refreshToken());

            given(jwtService.refreshAccessToken(any(RefreshAccessTokenRequest.class)))
                    .willReturn(refreshedTestToken);

            // when
            ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/refresh")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(refreshAccessTokenRequest)));

            // then
            resultActions
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(1008))
                    .andExpect(jsonPath("$.data.access_token").value(refreshedTestToken.accessToken()))
                    .andExpect(jsonPath("$.data.refresh_token").value(refreshedTestToken.refreshToken()));
        }

        @DisplayName("1009 : 토큰 리프레시 실패 - 리프레시 토큰이 만료된 경우")
        @WithMockCustomMember
        @Test
        void failToRefreshAccessTokenWhenExpiredRefreshToken() throws Exception {
            // given
            JwtPair testToken = JwtPair.from("fake-access-token", "fake-refresh-token");
            RefreshAccessTokenRequest refreshAccessTokenRequest= new RefreshAccessTokenRequest(testToken.refreshToken());

            given(jwtService.refreshAccessToken(any(RefreshAccessTokenRequest.class))).will(
                    invocation -> {
                        throw new JwtExpiredRefreshTokenException();
                    }
            );

            // when
            ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/refresh")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(refreshAccessTokenRequest)));

            // then
            resultActions
                    .andExpect(status().isForbidden())
                    .andExpect(jsonPath("$.code").value(ErrorCode.JWT_EXPIRED_REFRESH_TOKEN.getCode()))
                    .andExpect(jsonPath("$.data.message").value(ErrorCode.JWT_EXPIRED_REFRESH_TOKEN.getMessage()));
        }

        @DisplayName("1010 : 토큰 리프레시 실패 - 리프레시 토큰이 유효하지 않은 경우")
        @WithMockCustomMember
        @Test
        void failToRefreshAccessTokenWhenInvalidRefreshToken() throws Exception {
            // given
            JwtPair testToken = JwtPair.from("fake-access-token", "fake-refresh-token");
            RefreshAccessTokenRequest refreshAccessTokenRequest= new RefreshAccessTokenRequest(testToken.refreshToken());

            given(jwtService.refreshAccessToken(any(RefreshAccessTokenRequest.class))).will(
                    invocation -> {
                        throw new JwtInvalidRefreshTokenException();
                    }
            );

            // when
            ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/refresh")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(refreshAccessTokenRequest)));

            // then
            resultActions
                    .andExpect(status().isForbidden())
                    .andExpect(jsonPath("$.code").value(ErrorCode.JWT_INVALID_REFRESH_TOKEN.getCode()))
                    .andExpect(jsonPath("$.data.message").value(ErrorCode.JWT_INVALID_REFRESH_TOKEN.getMessage()));
        }
    }
}
