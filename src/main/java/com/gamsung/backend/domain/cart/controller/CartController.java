package com.gamsung.backend.domain.cart.controller;

import com.gamsung.backend.domain.cart.dto.request.CartDeleteRequest;
import com.gamsung.backend.domain.cart.dto.request.CartEntryRequest;
import com.gamsung.backend.domain.cart.dto.response.CartDeleteResponse;
import com.gamsung.backend.domain.cart.dto.response.CartEntryResponse;
import com.gamsung.backend.domain.cart.dto.response.CartFindResponse;
import com.gamsung.backend.domain.cart.service.CartService;
import com.gamsung.backend.global.common.ApiResponse;
import com.gamsung.backend.global.config.UserDetailsConfig;
import com.gamsung.backend.global.resolver.AuthContext;
import com.gamsung.backend.global.resolver.MemberAuth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
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

    @PostMapping
    @Operation(summary = "장바구니에 상품 추가 API", description = ENTRY_MY_CART)
    public ResponseEntity<ApiResponse<CartEntryResponse>> entryMyCart(@Valid @RequestBody CartEntryRequest cartEntryRequest
    , @Parameter(hidden = true) @MemberAuth AuthContext authContext) {
        Long memberId = authContext.id();
        cartService.entryMyCart(cartEntryRequest,memberId);
        CartEntryResponse responseData = CartEntryResponse.create();
        return ResponseEntity.ok(ApiResponse.create(4001,responseData));
    }


    @GetMapping
    @Operation(summary = "장바구니 목록 확인 API", description = FIND_MY_CART)
    public ResponseEntity<ApiResponse<List<CartFindResponse>>> findMyCart(@Parameter(hidden = true) @MemberAuth AuthContext authContext) {
        Long memberId = authContext.id();
        List<CartFindResponse> myCartList = cartService.findMyCart(memberId);
        return ResponseEntity.ok(ApiResponse.create(4000,myCartList));
    }

    @DeleteMapping
    @Operation(summary = "장바구니 삭제 API", description = DELETE_MY_CART)
    public ResponseEntity<ApiResponse<CartDeleteResponse>> deleteMyCart(@Valid @RequestBody CartDeleteRequest cartDeleteRequest,
                                                                        @Parameter(hidden = true) @MemberAuth AuthContext authContext) {
        Long memberId = authContext.id();
        System.out.println(cartDeleteRequest.getDeleteId());
        cartService.deleteMyCart(cartDeleteRequest,memberId);
        CartDeleteResponse responseData = CartDeleteResponse.create();
        return ResponseEntity.ok(ApiResponse.create(4003,responseData));
    }
}
