package com.gamsung.backend.global.exception;

import com.gamsung.backend.domain.order.exception.OrderSoldOutException;
import com.gamsung.backend.global.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<ErrorMessage>> handleBaseException(BaseException e) {
        return ResponseEntity.badRequest().body(
                ApiResponse.create(Integer.parseInt(e.getCode()), ErrorMessage.create(e.getMessage()))
        );
    }

    @ExceptionHandler(UnAuthException.class)
    public ResponseEntity<ApiResponse<ErrorMessage>> handleUnAuthException(UnAuthException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ApiResponse.create(Integer.parseInt(e.getCode()), ErrorMessage.create(e.getMessage()))
        );
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiResponse<ErrorMessage>> handleForbiddenException(ForbiddenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                ApiResponse.create(Integer.parseInt(e.getCode()), ErrorMessage.create(e.getMessage()))
        );
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse<ErrorMessage>> handleBindValidationError(BindException e) {
        ErrorCode validationError = ErrorCode.VALIDATION_ERROR;
        return ResponseEntity.badRequest().body(
                ApiResponse.create(Integer.parseInt(validationError.getCode()),
                        ErrorMessage.create(validationError.getMessage()))
        );
    }

    @ExceptionHandler(BookDateUnavailableException.class)
    public ResponseEntity<ApiResponse<ErrorMessage>> handleBookDateUnavailableException(BookDateUnavailableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ApiResponse.create(Integer.parseInt(e.getCode()), ErrorMessage.create(e.getMessage()))
        );
    }

    @ExceptionHandler(OrderSoldOutException.class)
    public ResponseEntity<ApiResponse<ErrorMessage>> handleOrderSoldOutException(OrderSoldOutException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ApiResponse.create(Integer.parseInt(e.getCode()), ErrorMessage.create(e.getMessage()))
        );
    }
}
