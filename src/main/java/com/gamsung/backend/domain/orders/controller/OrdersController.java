package com.gamsung.backend.domain.orders.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gamsung.backend.global.config.SwaggerDescriptionConfig.*;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrdersController {

    @GetMapping("/me")
    @Operation(summary = "예약 내역 조회 API", description = MY_ORDER)
    public ResponseEntity<String> myOrder() {
        /**
         * 테스트 데이터가 아닌 경우 이 응답 방식을 사용하면 됩니다.
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .code(HttpStatus.OK.value())
                        .data(tripService.getAllTripsPaging(pageable))
                        .build()
        );
         **/

        return ResponseEntity.ok("""
                        {
                 	"code" : 2000,
                 	"data" : [
                 	  {
                 				"order_date" : "2023-11-23",
                 				"accommodation_id" : 3,
                 				"accommodation_name" : "로터스모텔",
                 				"accommodation_img" : "https://cdn.discordapp.com/attachments/1177157251836948490/1177161123817066518/5b82230c9374c02c.PNG?ex=65717fba&is=655f0aba&hm=d3ac9e70d92c95c4968d7d8bdc6e4e1708c091155030215fe79cce44f5d0d8ca&",
                 				"people_number" : 1,
                 				"start_date" : "2023-11-23",
                 				"end_date" : "2023-11-24",
                 				"representative_name" : "박건우",
                 				"order_price" : 50000
                 		},
                 	  {
                 				"order_date" : "2023-11-24",
                 				"accommodation_id" : 3,
                 				"accommodation_name" : "신라호텔",
                 				"accommodation_img" : "https://cdn.discordapp.com/attachments/1177157251836948490/1177159849205841960/62e03f6768aa5eb9.PNG?ex=65717e8a&is=655f098a&hm=7f02b43628e84e19b3326c476251441ef796fd125d5b0e730f8b00bf38458287&",
                 				"people_number" : 2,
                 				"start_date" : "2023-11-24",
                 				"end_date" : "2023-11-27",
                 				"representative_name" : "한상우",
                 				"order_price" : 100000
                 		}
                 	]
                 }
                """);
    }

    @GetMapping("/check")
    @Operation(summary = "예약 가능 조회 API", description = CALENDAR_CHECK)
    public ResponseEntity<String> calendarCheck() {

        return ResponseEntity.ok("""
                    {
                    	"code" : 2001,
                    	"data" : {
                    	  "message": "해당 날짜는 예약 가능합니다."
                    	}
                    }
                """);
    }

    @PostMapping
    @Operation(summary = "결제 성공시 예약 내역 추가 API", description = ENTRY_ORDER)
    public ResponseEntity<String> entryOrder() {

        return ResponseEntity.ok(
                """
                          {
                          	"code" : 2004,
                          	"data" : {
                          	  "message" : "결제가 성공하였습니다." 
                          	}
                          }
                        """
        );
    }

}
