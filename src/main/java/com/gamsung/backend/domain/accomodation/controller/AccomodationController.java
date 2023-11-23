package com.gamsung.backend.domain.accomodation.controller;

import com.gamsung.backend.domain.member.dto.request.MemberLoginRequest;
import com.gamsung.backend.global.common.ApiResponse;
import com.gamsung.backend.global.config.SwaggerDescriptionConfig;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.gamsung.backend.global.config.SwaggerDescriptionConfig.ALL_ENTRY_DESCRIPTION;
import static com.gamsung.backend.global.config.SwaggerDescriptionConfig.ENTRY_DESCRIPTION;


@RestController
@RequestMapping("/v1/accomodations")
@RequiredArgsConstructor
public class AccomodationController {



    @GetMapping("/all-entry")
    @Operation(summary = "메인 페이지 숙소 정보 가져오는 API", description = ALL_ENTRY_DESCRIPTION)
    public ResponseEntity<String> accomodationsAllEntry() {

        return ResponseEntity.ok(
                """
                                {
                                	"code" : 3000,
                                	"data" : [
                                 		{
                                 			"accommodation_id" : 1,
                                 			"accomodation_name" : "리거 로얄 라구니 괌 리조트,"
                                 			"short_address" : "경기도 경기시",		
                                 			"accomodation_price" : 240000,
                                       "accomodation_img" : "https://cdn.discordapp.com/attachments/1177157251836948490/1177157255473410108/4eada5e5bdbebe05.PNG?ex=65717c20&is=655f0720&hm=826344f5152a26e46b094369569d9fdab3ef387fe117f7686fbf28ded898254c&",
                                 		},
                                 		{
                                 			"accommodation_id" : 6,
                                 			"accomodation_name" : "리거 로얄 라구니 괌 리조트",
                                 			"short_address" : "경기도 고양시",		
                                 			"accomodation_price" : 360000,
                                       "accomodation_img" : "https://cdn.discordapp.com/attachments/1177157251836948490/1177157471291330560/3176fe417dfaf6b9.PNG?ex=65717c53&is=655f0753&hm=dca5f9ad96de35617077640708c533bc4016a3d55493d6822670c23bbd353761&",
                                 		},
                                 		{
                                 			"accommodation_id" : 7,
                                 			"accomodation_name" : "리거 로얄 라구니 괌 리조트",
                                 			"short_address" : "경기도 수원시",		
                                 			"accomodation_price" : 40000,
                                       "accomodation_img" : "https://cdn.discordapp.com/attachments/1177157251836948490/1177157751462428672/91ccfa75c5b3872b.PNG?ex=65717c96&is=655f0796&hm=3de6d85fa5e4e28164727ba4075917f3fc8964d46f20e393b7f9c9c4a547d5d9&",
                                 		},
                                 		...		
                                 ]
                                }
                        """);
    }


    @GetMapping
    @Operation(summary = "상세 페이지 숙소 정보 가져오는 API", description = ENTRY_DESCRIPTION)
    public ResponseEntity<String> accomodationsEntry(
    ) {

        return ResponseEntity.ok(
                """
                        "code" : 3001,
                        "data" : {
                        	"accomodation_name" : "신라 호텔",
                        	"description" : "신라 호텔 입니다.",
                        	"address" : "상세주소",
                        	"limit_people" : 4,
                        	"accomodation_price" : 100000,
                          "accomodation_img" : "https://cdn.discordapp.com/attachments/1177157251836948490/1177159849205841960/62e03f6768aa5eb9.PNG?ex=65717e8a&is=655f098a&hm=7f02b43628e84e19b3326c476251441ef796fd125d5b0e730f8b00bf38458287&",
                        	"room_img" : [
                        			"https://cdn.discordapp.com/attachments/1177157251836948490/1177159187957030912/0172c2c57ca8bae5.PNG?ex=65717dec&is=655f08ec&hm=a2ff87c70eeb940cb849e6a81f5e57ca51e2b63eec936555e14937ad56793479&",
                        			"https://cdn.discordapp.com/attachments/1177157251836948490/1177159269460746280/a36bf3578a9fe8d0.PNG?ex=65717e00&is=655f0900&hm=d78cc3c34489ae6e761d2d5610e659e7f4286f46ce7e2d0e99010f97fd69b286&",
                        			"https://cdn.discordapp.com/attachments/1177157251836948490/1177159352612831303/4444d6a3208a5111.PNG?ex=65717e14&is=655f0914&hm=327f6eeb6bf94c9d7b22ca4cc70c155f4ef5417ef3dc88cd52556130465939f9&"
          
                        	]
                        }
                              
                        """);
    }


}
