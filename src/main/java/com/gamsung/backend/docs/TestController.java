package com.gamsung.backend.docs;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gamsung.backend.global.config.SwaggerDescriptionConfig.*;

@RestController
@RequestMapping("/v1/global-error")
@RequiredArgsConstructor
public class TestController {

    @PostMapping("/accesstoken")
    @Operation(summary = "유효하지 않은 액세스 토큰 공통 오류 체크용 API", description = GLOBAL_ACCESSTOKEN_ERROR)
    public ResponseEntity<String> notAllowAccessToken() {

        return ResponseEntity.ok(
                """
                       {
                           "code" : 5000,
                           "data" : {
                             "message" : "유효하지 않은 엑세스 토큰입니다."
                                    }
                        }
                        """);

    }

    @PostMapping("/expiredaccesstoken")
    @Operation(summary = "유효기간 만료된 액세스 토큰 통한 API 접근 오류 체크용 API", description = EXPIREDACCESSTOKEN)
    public ResponseEntity<String> expiredAccessToken() {

        return ResponseEntity.ok(
                """
                        {
                            	"code" : 5001,
                            	"data" : {
                            	  "message" : "엑세스 토큰이 만료되었습니다."
                            	}
                            }
                        """);

    }

    @PostMapping("/serviceerror")
    @Operation(summary = "백엔드 서버(DB) 오류 체크용 API", description = SERVICEERROR)
    public ResponseEntity<String> serviceErrorToken() {

        return ResponseEntity.ok(
                """
                        {
                        	"code" : 5002,
                        	"data" : {
                        	  "message" : "서비스 오류입니다."
                        	}
                        }
                        """);

    }
}
