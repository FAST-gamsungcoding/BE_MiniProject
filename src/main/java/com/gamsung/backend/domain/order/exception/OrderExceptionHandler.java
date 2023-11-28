package com.gamsung.backend.domain.order.exception;

import com.gamsung.backend.global.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.gamsung.backend")
public class OrderExceptionHandler {

    @ExceptionHandler(BookDateUnavailableException.class)
    public ResponseEntity<ApiResponse<String>> handleBookDateUnavailableException(
            BookDateUnavailableException e){
        return ResponseEntity.ok(ApiResponse.create(
                e.getErrorCode().getStatus(), e.getErrorCode().getMessage()
        ));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleOrderNotFoundException(
            OrderNotFoundException e){
        return ResponseEntity.ok(ApiResponse.create(
                e.getErrorCode().getStatus(), e.getErrorCode().getMessage()
        ));
    }

    @ExceptionHandler(OrderSoldOutException.class)
    public ResponseEntity<ApiResponse<String>> handleOrderSoldOutException(
            OrderSoldOutException e){
        return ResponseEntity.ok(ApiResponse.create(
                e.getErrorCode().getStatus(), e.getErrorCode().getMessage()
        ));
    }
}
