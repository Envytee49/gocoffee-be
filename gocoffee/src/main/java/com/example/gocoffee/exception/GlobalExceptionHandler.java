package com.example.gocoffee.exception;

import com.example.gocoffee.dto.response.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleAppException(AppException exception) {
        log.error("handleAppException");
        log.error(exception.getMessage());
        return ApiResponse.builder()
                .errorCode(exception.getErrorCode().getCode())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<?> handleUnauthorizedException(AuthenticationException exception) {
        log.error("AuthenticationException");
        log.error(exception.getMessage());
        return ApiResponse.builder()
                .errorCode(401)
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<?> handleInternalServerException(InternalServerException exception) {
        log.error("InternalServerException");
        log.error(exception.getMessage());
        return ApiResponse.builder()
                .errorCode(500)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleMismatchParametersType(MethodArgumentTypeMismatchException exception) {
        log.error("handleMismatchParametersType");
        String message = "The required type of " +
                exception.getPropertyName() +
                " is " +
                Objects.requireNonNull(exception.getRequiredType()).getSimpleName();
        log.error(message);
        return ApiResponse.builder()
                .errorCode(ErrorCode.INVALID_PARAMETER_TYPE.getCode())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleParameterNotValid(MethodArgumentNotValidException exception) {
        log.error("handleParameterNotValid");
        log.error(Objects.requireNonNull(exception.getFieldError()).getDefaultMessage());
        return ApiResponse.builder()
                .errorCode(ErrorCode.PARAMETER_NOT_VALID.getCode())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(Objects.requireNonNull(exception.getFieldError()).getDefaultMessage())
                .build();
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleConstraintViolation(ConstraintViolationException exception) {
        log.error("handleConstraintViolation");
        log.error(exception.getMessage());
        return ApiResponse.builder()
                .errorCode(ErrorCode.INVALID_PARAMETER_DATA.getCode())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        log.error("handleMissingServletRequestParameterException");
        log.error(exception.getMessage());
        return ApiResponse.builder()
                .errorCode(ErrorCode.INVALID_PARAMETER_DATA.getCode())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(exception.getParameterName() + " is missing.")
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleNotReadableException(HttpMessageNotReadableException exception) {
        log.error("handleNotReadableException");
        log.error(ErrorCode.INVALID_DATA_FORMAT.getMessage());
        return ApiResponse.builder()
                .errorCode(ErrorCode.INVALID_DATA_FORMAT.getCode())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handleNoResourceFoundException(NoResourceFoundException exception) {
        log.error("handleNoResourceFoundException");
        log.error(exception.getMessage());
        return ApiResponse.builder()
                .errorCode(ErrorCode.NO_RESOURCE_FOUND.getCode())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ErrorCode.NO_RESOURCE_FOUND.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<?> handleUnknownException(Exception exception) {
        exception.printStackTrace();
        return ApiResponse.builder()
                .errorCode(ErrorCode.UNKNOWN.getCode())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ErrorCode.UNKNOWN.getMessage())
                .build();
    }
}
