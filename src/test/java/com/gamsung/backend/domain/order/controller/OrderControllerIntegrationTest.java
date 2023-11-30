package com.gamsung.backend.domain.order.controller;

import com.gamsung.backend.domain.member.dto.request.MemberLoginRequest;
import com.gamsung.backend.domain.member.dto.request.MemberRegisterRequest;
import com.gamsung.backend.domain.member.dto.response.MemberLoginResponse;
import com.gamsung.backend.domain.member.entity.Member;
import com.gamsung.backend.domain.member.service.MemberService;
import com.gamsung.backend.global.common.BaseRedisContainerTest;
import com.gamsung.backend.global.factory.MemberTestFactory;
import com.gamsung.backend.global.jwt.JwtPair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;


@DisplayName("OrderController 통합 테스트")
public class OrderControllerIntegrationTest extends BaseRedisContainerTest {

    private static final String JWT_TOKEN_PREFIX = "Bearer ";

    @Autowired
    private MemberService memberService;

    private JwtPair jwtPair;

    @BeforeEach
    public void setUp() {
        Member member = MemberTestFactory.createMemberWithRandomValues(false);
        memberService.register(
                new MemberRegisterRequest(member.getEmail(), member.getPassword(), member.getName())
        );
        MemberLoginResponse memberLoginResponse = memberService.login(
                new MemberLoginRequest(member.getEmail(), member.getPassword())
        );
        jwtPair = JwtPair.builder()
                .accessToken(memberLoginResponse.accessToken())
                .refreshToken(memberLoginResponse.refreshToken())
                .build();
    }

    @Test
    @DisplayName("숙소 여러개 예약하기")
    @Transactional
    void orderSuccessTest() throws Exception {

        //given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, JWT_TOKEN_PREFIX + jwtPair.getAccessToken());

        String jsonPayload = """
                [
                  {
                    "accommodation_id": 2,
                    "people_number": 2,
                    "start_date": "2023-12-03",
                    "end_date": "2023-12-04",
                    "representative_name": "홍길동",
                    "representative_email": "rlfehd@naver.com",
                    "order_price": 120000,
                    "cart_id": 0
                  },
                  {
                    "accommodation_id": 1,
                    "people_number": 2,
                    "start_date": "2023-12-02",
                    "end_date": "2023-12-03",
                    "representative_name": "홍길동",
                    "representative_email": "rlfehd@naver.com",
                    "order_price": 120000,
                    "cart_id": 0
                  }
                ]""";

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/order")
                        .headers(headers)
                        .content(jsonPayload)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(2003))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message").value("결제가 성공하였습니다."))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @DisplayName("숙소 예약 가능여부 확인하기")
    @Transactional
    void checkBookDateIntegrationTest() throws Exception {

        //given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, JWT_TOKEN_PREFIX + jwtPair.getAccessToken());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/order/check")
                        .param("accommodation_id", "1")
                        .param("start_date", "2023-12-01")
                        .param("end_date", "2023-12-03")
                        .headers(headers))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(2001))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message").value("해당 날짜는 예약 가능합니다."))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @DisplayName("내가 예약한 숙소들 조회하기")
    @Transactional
    void getUserOrderListIntegrationTest() throws Exception {

        //given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, JWT_TOKEN_PREFIX + jwtPair.getAccessToken());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/order/me")
                        .headers(headers))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(2000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.order_list").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.total_page").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.page_number").exists())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
