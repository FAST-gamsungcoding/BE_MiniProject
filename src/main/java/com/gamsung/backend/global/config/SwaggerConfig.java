package com.gamsung.backend.global.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Gamsung API")
                        .version("v1")
                        .description("## HTTP 상태 코드 헤더 규약\n" +
                                "\n" +
                                "---\n" +
                                "\n" +
                                "| 코드 | 설명 |\n" +
                                "| --- | --- |\n" +
                                "| 200 | 요청 성공(Ok) |\n" +
                                "| 201 | 리소스 생성 성공시 사용(Created) |\n" +
                                "| 400 | 잘못된 파라미터 요청이 들어왔을 경우(Bad Request) |\n" +
                                "| 401 | 인증되지 않은 상태에서 인증이 필요한 리소스에 접근 (Unauthorized) |\n" +
                                "| 404 | 존재하지 않는 리소스에 접근할 때 발생(Not Found) |\n" +
                                "| 408 | DB 서버가 TimeOut 발생했을 경우(Request Timeout)(통신 오류) |\n" +
                                "| 500 | 서비스 오류 |\n" +
                                "\n" +
                                "## 서비스 코드 규약\n" +
                                "\n" +
                                "---\n" +
                                "\n" +
                                "| 코드 | 설명 |\n" +
                                "| --- | --- |\n" +
                                "| 1000 | 로그인 성공 |\n" +
                                "| 1001 | 로그인 요청 - 아이디 틀렸을 경우 |\n" +
                                "| 1002 | 로그인 요청 - 비밀번호 틀렸을 경우 |\n" +
                                "| 1003 | 회원가입 성공 |\n" +
                                "| 1004 | 회원가입 실패 - 이메일 유효성 검사 탈락 |\n" +
                                "| 1005 | 회원가입 실패 - 비밀번호 유효성 검사 탈락 |\n" +
                                "| 1006 | 아이디 중복체크 - 가입 가능 이메일(중복이 아님) |\n" +
                                "| 1007 | 아이디 중복체크 - 가입 불가능한 이메일(중복임) |\n" +
                                "| 1008 | 토큰 리프레시 성공 |\n" +
                                "| 1009 | 토큰 리프레시 실패 - 리프레시 토큰이 만료된 경우 |\n" +
                                "| 1010 | 토큰 리프레시 실패 - 리프레시 토큰이 유효하지 않은 경우 |\n" +
                                "| 1011 | 로그아웃 성공 |\n" +
                                "| 2000 | 예약 내역 조회 성공 |\n" +
                                "| 2001 | 예약여부 확인 - 예약이 가능한 날짜 |\n" +
                                "| 2002 | 예약여부 확인 - 예약이 불가능한 날짜 |\n" +
                                "| 2003 | 예약여부 확인 - 해당 id의 숙소를 찾을 수 없음 |\n" +
                                "| 2004 | 결제(예약) 성공 |\n" +
                                "| 3000 | 카테고리로 상품 조회 성공 |\n" +
                                "| 3001 | 개별 상품 조회 성공 |\n" +
                                "| 3002 | 개별 상품 조회 실패 - 해당 상품이 없는 경우 |\n" +
                                "| 4000 | 장바구니 조회 요청 성공 |\n" +
                                "| 4001 | 장바구니 등록 요청 성공 |\n" +
                                "| 4002 | 장바구니 등록 요청 실패 - 장바구니 내용이 10개 초과인 경우 |\n" +
                                "| 4003 | 장바구니 삭제 성공 |\n" +
                                "| 4004 | 장바구니 삭제 실패 - 장바구니 id가 존재하지 않을 경우 |\n" +
                                "| 5000 | 유효하지 않은 엑세스 토큰 (Unauthorized) |\n" +
                                "| 5001 | 엑세스 토큰 만료 (Unauthorized) |\n" +
                                "| 5002 | 서비스 오류 |\n" +
                                "\n" +
                                "# 공통 오류\n" +
                                "\n" +
                                "## 유효하지 않은 엑세스 토큰\n" +
                                "\n" +
                                "## 바디\n" +
                                "\n" +
                                "### 본문\n" +
                                "\n" +
                                "| 타입 | 필드명 | 설명 |\n" +
                                "| --- | --- | --- |\n" +
                                "| Number | code | 규약 코드 |\n" +
                                "| Data | data | 응답 내용 |\n" +
                                "\n" +
                                "### Data\n" +
                                "\n" +
                                "| 타입 | 필드명 | 설명 |\n" +
                                "| --- | --- | --- |\n" +
                                "| String | message | 오류 메시지 |\n" +
                                "\n" +
                                "```json\n" +
                                "{\n" +
                                "\t\"code\" : 5000,\n" +
                                "\t\"data\" : {\n" +
                                "\t  \"message\" : \"유효하지 않은 엑세스 토큰입니다.\"\n" +
                                "\t}\n" +
                                "}\n" +
                                "```\n" +
                                "\n" +
                                "## 엑세스 토큰 만료\n" +
                                "\n" +
                                "## 바디\n" +
                                "\n" +
                                "### 본문\n" +
                                "\n" +
                                "| 타입 | 필드명 | 설명 |\n" +
                                "| --- | --- | --- |\n" +
                                "| Number | code | 규약 코드 |\n" +
                                "| Data | data | 응답 내용 |\n" +
                                "\n" +
                                "### Data\n" +
                                "\n" +
                                "| 타입 | 필드명 | 설명 |\n" +
                                "| --- | --- | --- |\n" +
                                "| String | message | 오류 메시지 |\n" +
                                "\n" +
                                "```json\n" +
                                "{\n" +
                                "\t\"code\" : 5001,\n" +
                                "\t\"data\" : {\n" +
                                "\t  \"message\" : \"엑세스 토큰이 만료되었습니다.\"\n" +
                                "\t}\n" +
                                "}\n" +
                                "```\n" +
                                "\n" +
                                "## 서비스 오류\n" +
                                "\n" +
                                "## 바디\n" +
                                "\n" +
                                "### 본문\n" +
                                "\n" +
                                "| 타입 | 필드명 | 설명 |\n" +
                                "| --- | --- | --- |\n" +
                                "| Number | code | 규약 코드 |\n" +
                                "| Data | data | 응답 내용 |\n" +
                                "\n" +
                                "### Data\n" +
                                "\n" +
                                "| 타입 | 필드명 | 설명 |\n" +
                                "| --- | --- | --- |\n" +
                                "| String | message | 오류 메시지 |\n" +
                                "\n" +
                                "```java\n" +
                                "<서비스 오류(DB연결, 트랜잭션 오류 등)>\n" +
                                "{\n" +
                                "\t\"code\" : 5002,\n" +
                                "\t\"data\" : {\n" +
                                "\t  \"message\" : \"서비스 오류입니다.\"\n" +
                                "\t}\n" +
                                "}\n" +
                                "```")
                        .contact(new Contact().name("갬성코딩")));
    }
}
