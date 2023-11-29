package com.gamsung.backend.domain.order.controller;

import com.gamsung.backend.domain.order.dto.request.OrderAccommodationRequest;
import com.gamsung.backend.domain.order.dto.response.BookDateAvailableResponse;
import com.gamsung.backend.domain.order.dto.response.OrderAccommodationResponse;
import com.gamsung.backend.domain.order.dto.response.OrderResponse;
import com.gamsung.backend.domain.order.service.OrderService;
import com.gamsung.backend.global.common.ApiResponse;
import com.gamsung.backend.global.resolver.AuthContext;
import com.gamsung.backend.global.resolver.MemberAuth;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "숙소 리스트 결제 API", description = "숙소들을 결제하여 예약합니다.")
    public ResponseEntity<ApiResponse<OrderAccommodationResponse>> orderAccommodation(@RequestBody @Validated List<OrderAccommodationRequest> orderAccommodationRequestList,
                                                                                      @MemberAuth AuthContext authContext
    ){
        OrderAccommodationResponse response = orderService.orderAccommodation(orderAccommodationRequestList, authContext.id());
        return ResponseEntity.ok(ApiResponse.create(2003, response));
    }

    @GetMapping("/check")
    @Operation(summary = "예약 가능 확인 API", description = "결제가 완료된 예약 리스트를 조회합니다.")
    public ResponseEntity<ApiResponse<BookDateAvailableResponse>> checkBookDate(@RequestParam("accommodation_id") long id,
                                                     @RequestParam("start_date")LocalDate startDate,
                                                     @RequestParam("end_date") LocalDate endDate) {
        BookDateAvailableResponse response = orderService.checkBookDate(id, startDate, endDate);
        return ResponseEntity.ok(ApiResponse.create(2001, response));
    }

    @GetMapping("/me")
    @Operation(summary = "예약 조회 API", description = "해당 날짜로 예약 가능한지 확인합니다.")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getUserOrderList(@PageableDefault(
            page = 0, size = 8) Pageable pageable, @MemberAuth AuthContext authContext) {
        List<OrderResponse> response = orderService.getMemberOrdersList(pageable, authContext.id());
        return ResponseEntity.ok(ApiResponse.create(2000, response));
    }

}
