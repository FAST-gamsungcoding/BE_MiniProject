package com.gamsung.backend.domain.cart.controller;

import com.gamsung.backend.domain.cart.dto.request.CartEntryRequest;
import com.gamsung.backend.domain.cart.dto.response.CartFindResponse;
import com.gamsung.backend.domain.cart.service.CartService;
import com.gamsung.backend.global.common.ApiResponse;
import com.gamsung.backend.global.config.UserDetailsConfig;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gamsung.backend.global.config.SwaggerDescriptionConfig.*;

@RestController
@RequestMapping("/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    @Operation(summary = "장바구니 목록 확인 API", description = FIND_MY_CART)
    public ResponseEntity<ApiResponse<List<CartFindResponse>>> findMyCart() {

        List<CartFindResponse> myCartList = cartService.findMyCart();
        return ResponseEntity.ok(ApiResponse.create(4000,myCartList));


    }

    @PostMapping
    @Operation(summary = "장바구니에 상품 추가 API", description = ENTRY_MY_CART)
    public ResponseEntity<ApiResponse<String>> entryMyCart(@RequestBody CartEntryRequest cartEntryRequest) {
        cartService.entryMyCart(cartEntryRequest);
        return ResponseEntity.ok(ApiResponse.create(4001,"장바구니에 추가되었습니다."));
    }

    @DeleteMapping
    @Operation(summary = "장바구니 삭제 API", description = DELETE_MY_CART)
    public ResponseEntity<ApiResponse<String>> deleteMyCart(@PathVariable long[] deleteId) {

        cartService.deleteMyCart(deleteId);


        return ResponseEntity.ok(ApiResponse.create(4003,"선택한 항목들이 삭제되었습니다."));

    }
}
