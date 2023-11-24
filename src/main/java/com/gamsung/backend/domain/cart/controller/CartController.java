package com.gamsung.backend.domain.cart.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.gamsung.backend.global.config.SwaggerDescriptionConfig.*;

@RestController
@RequestMapping("/v1/cart")
@RequiredArgsConstructor
public class CartController {

    @GetMapping
    @Operation(summary = "장바구니 목록 확인 API", description = FIND_MY_CART)
    public ResponseEntity<String> findMyCart() {

        return ResponseEntity.ok("""
                    {
                    "code" : 4000,
                    "data" : [
                        {
                          "cart_id" : 1,
                          "product_id" : 1,
                          "accomodation_name" : "신라호텔",
                    			"address" : "서울시 강남구 청담대로 9",
                    			"start_date" : "2023-01-23",
                    			"end_date" : "2023-01-30",
                    			"people_number" : 2,
                    			"cart_price" : 150000,
                          "accomodation_img" :"https://cdn.discordapp.com/attachments/1177157251836948490/1177159849205841960/62e03f6768aa5eb9.PNG?ex=65717e8a&is=655f098a&hm=7f02b43628e84e19b3326c476251441ef796fd125d5b0e730f8b00bf38458287&",
                        },
                    		{
                          "cart_id" : 1,
                          "product_id" : 1,
                          "accomodation_name" : "로터스 모텔",
                    			"address" : "서울시 강남구 도산대로 15길",
                    			"start_date" : "2023-01-23",
                    			"end_date" : "2023-01-30",
                    			"people_number" : 2,
                    			"cart_price" : 70000,
                          "accomodation_img" :"https://cdn.discordapp.com/attachments/1177157251836948490/1177161123817066518/5b82230c9374c02c.PNG?ex=65717fba&is=655f0aba&hm=d3ac9e70d92c95c4968d7d8bdc6e4e1708c091155030215fe79cce44f5d0d8ca&",
                        }
                      ]
                    }
                """);
    }

    @PostMapping
    @Operation(summary = "장바구니에 상품 추가 API", description = ENTRY_MY_CART)
    public ResponseEntity<String> entryMyCart() {

        return ResponseEntity.ok("""
                   {
                     	"code" : 4003,
                     	"data" : {
                     	  "message" : "장바구니에 추가되었습니다."
                     	}
                     }
                """);
    }

    @DeleteMapping
    @Operation(summary = "장바구니 삭제 API", description = DELETE_MY_CART)
    public ResponseEntity<String> deleteMyCart() {

        return ResponseEntity.ok(""" 
                {
                	"code" : 4003,
                	"data" : {
                	  "message" : "선택한 항목들이 삭제되었습니다."
                }
                """);

    }
}