package com.gamsung.backend.global.exception;


import com.gamsung.backend.domain.cart.exception.CartException;
import com.gamsung.backend.global.common.ApiResponse;
import jakarta.persistence.RollbackException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        ErrorCode validationError = ErrorCode.DATA_NOT_ALLOW_TYPE;
        return ResponseEntity.badRequest().body(
                ApiResponse.create(Integer.parseInt(validationError.getCode()),
                        ErrorMessage.create(validationError.getMessage()))
        );
    }

    @ExceptionHandler(CartException.class)
    public ResponseEntity<ApiResponse<ErrorMessage>> handleCartException(CartException e) {
        // ErrorResponse 클래스는 예외를 클라이언트에게 전달할 때 사용될 구조입니다.
        return ResponseEntity.badRequest().body(
                ApiResponse.create(Integer.parseInt(e.getCode()),
                        ErrorMessage.create(e.getMessage()))
        );
    }

    @ExceptionHandler(CannotGetJdbcConnectionException.class)
    public ResponseEntity<ApiResponse<ErrorMessage>> handleJdbcException(CannotGetJdbcConnectionException e) {
        ErrorCode validationError = ErrorCode.SERVICE_ERREOR;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponse.create(Integer.parseInt(validationError.getCode()),
                        ErrorMessage.create(validationError.getMessage()))
        );
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiResponse<ErrorMessage>> handleDataAccessException(DataAccessException e) {
        ErrorCode validationError = ErrorCode.SERVICE_ERREOR;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponse.create(Integer.parseInt(validationError.getCode()),
                        ErrorMessage.create(validationError.getMessage()))
        );
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<ApiResponse<ErrorMessage>> handleBadSqlException(BadSqlGrammarException e) {
        ErrorCode validationError = ErrorCode.SERVICE_ERREOR;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponse.create(Integer.parseInt(validationError.getCode()),
                        ErrorMessage.create(validationError.getMessage()))
        );
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ApiResponse<ErrorMessage>> handleTransactionException(TransactionSystemException e) {
        ErrorCode validationError = ErrorCode.SERVICE_ERREOR;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponse.create(Integer.parseInt(validationError.getCode()),
                        ErrorMessage.create(validationError.getMessage()))
        );
    }

    @ExceptionHandler(RollbackException.class)
    public ResponseEntity<ApiResponse<ErrorMessage>> handleRollbackException(RollbackException e) {
        // ErrorResponse 클래스는 예외를 클라이언트에게 전달할 때 사용될 구조입니다.
        ErrorCode validationError = ErrorCode.SERVICE_ERREOR;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponse.create(Integer.parseInt(validationError.getCode()),
                        ErrorMessage.create(validationError.getMessage()))
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ErrorMessage>> handleBindValidationError(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : null;

        BaseException baseException;

        if (errorMessage != null) {
            if (ValidationCode.DATA_NOT_ALLOW_TYPE.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.DATA_NOT_ALLOW_TYPE);
            }
            else {
                baseException = new BaseException(ErrorCode.SERVICE_ERREOR);
            }
        }
        else {
            baseException = new BaseException(ErrorCode.SERVICE_ERREOR);
        }
        return ResponseEntity.badRequest().body(
                ApiResponse.create(Integer.parseInt(baseException.getCode()),
                        ErrorMessage.create(baseException.getMessage()))
        );
    }
}
