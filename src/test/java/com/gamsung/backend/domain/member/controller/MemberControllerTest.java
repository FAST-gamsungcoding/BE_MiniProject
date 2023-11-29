package com.gamsung.backend.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamsung.backend.domain.member.controller.request.MemberControllerLoginRequest;
import com.gamsung.backend.domain.member.controller.request.MemberControllerRegisterRequest;
import com.gamsung.backend.domain.member.dto.request.MemberLoginRequest;
import com.gamsung.backend.domain.member.dto.request.MemberRegisterRequest;
import com.gamsung.backend.domain.member.dto.response.MemberLoginResponse;
import com.gamsung.backend.domain.member.dto.response.MemberLogoutResponse;
import com.gamsung.backend.domain.member.dto.response.MemberRegisterEmailCheckResponse;
import com.gamsung.backend.domain.member.dto.response.MemberRegisterResponse;
import com.gamsung.backend.domain.member.entity.Member;
import com.gamsung.backend.domain.member.service.MemberService;
import com.gamsung.backend.global.factory.MemberTestFactory;
import com.gamsung.backend.global.jwt.JwtPair;
import com.gamsung.backend.global.security.WithMockCustomMember;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@ActiveProfiles("test")
@MockBean(JpaMetamodelMappingContext.class)
class MemberControllerTest {

    private final static String BASE_URL = "/v1/member";
    private static final String JWT_TOKEN_PREFIX = "Bearer ";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @DisplayName("멤버 로그인")
    @WithMockUser
    @Nested
    class LoginMemberUnitTest {

        @DisplayName("1000 : 로그인 성공")
        @Test
        void successToLoginMember() throws Exception {
            // given
            Member testMember = MemberTestFactory.createMemberWithRandomValues(false);
            JwtPair testToken = JwtPair.builder()
                    .accessToken("fake-access-token")
                    .refreshToken("fake-refresh-token")
                    .build();
            MemberControllerLoginRequest memberControllerLoginRequest
                    = new MemberControllerLoginRequest(testMember.getEmail(), testMember.getEmail());
            MemberLoginResponse memberLoginResponse = MemberLoginResponse.from(testToken);
            given(memberService.login(any(MemberLoginRequest.class)))
                    .willReturn(memberLoginResponse);

            // when
            ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/login")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(memberControllerLoginRequest)));

            // then
            resultActions
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(1000))
                    .andExpect(jsonPath("$.data.access_token").value(memberLoginResponse.accessToken()))
                    .andExpect(jsonPath("$.data.refresh_token").value(memberLoginResponse.refreshToken()));
        }
    }

    @DisplayName("멤버 회원가입")
    @WithMockUser
    @Nested
    class RegisterMemberUnitTest {

        @DisplayName("1003 : 회원가입 성공")
        @Test
        void successToRegisterMember() throws Exception {
            // given
            Member testMember = MemberTestFactory.createMemberWithRandomValues(false);
            MemberControllerRegisterRequest memberControllerRegisterRequest = new MemberControllerRegisterRequest(
                    testMember.getEmail(),
                    testMember.getPassword(),
                    testMember.getName()
            );
            MemberRegisterResponse memberRegisterResponse = MemberRegisterResponse.create();
            given(memberService.register(any(MemberRegisterRequest.class)))
                    .willReturn(memberRegisterResponse);

            // when
            ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/register")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(memberControllerRegisterRequest)));

            // then
            resultActions
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.code").value(1003))
                    .andExpect(jsonPath("$.data.message").value(memberRegisterResponse.message()));
        }

        @DisplayName("1006 : 이메일 중복체크 - 가입 가능 이메일(중복이 아님)")
        @Test
        void successToRegisterEmailcheckMember() throws Exception {
            // given
            Member testMember = MemberTestFactory.createMemberWithRandomValues(false);
            MemberRegisterEmailCheckResponse memberRegisterEmailCheckResponse
                    = MemberRegisterEmailCheckResponse.create();
            given(memberService.emailCheck(anyString())).willReturn(memberRegisterEmailCheckResponse);

            // when
            ResultActions resultActions = mockMvc.perform(get(BASE_URL + "/register/check")
                    .param("email", testMember.getEmail())
                    .with(csrf()));

            // then
            resultActions
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(1006))
                    .andExpect(jsonPath("$.data.message").value(memberRegisterEmailCheckResponse.message()));
        }
    }


    @DisplayName("멤버 로그아웃")
    @Nested
    class LogoutMemberUnitTest {

        @DisplayName("1011 : 로그아웃 성공")
        @WithMockCustomMember
        @Test
        void successToLogoutMember() throws Exception {
            // given
            MemberLogoutResponse memberLogoutResponse = MemberLogoutResponse.create();
            given(memberService.logout(anyString())).willReturn(memberLogoutResponse);

            // when
            ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/logout")
                    .with(csrf())
                    .header(HttpHeaders.AUTHORIZATION, JWT_TOKEN_PREFIX + "fake-access-token")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(memberLogoutResponse)));

            // then
            resultActions
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(1011))
                    .andExpect(jsonPath("$.data.message").value(memberLogoutResponse.message()));
        }
    }
}