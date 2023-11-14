package com.davy.restapi.shared.handler;

import ch.qos.logback.core.spi.ErrorCodes;
import com.davy.restapi.shared.exceptions.RequestNotValidException;
import com.davy.restapi.shared.response.ApiErrorResponse;
import com.davy.restapi.shared.exceptions.ApplicationException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<?> handleApplicationException(
            final ApplicationException exception,
            final HttpServletRequest request
            ) {
        var response = ApiErrorResponse.builder()
                .errorCode(exception.getErrorCode())
                .message(exception.getMessage())
                .statusCode(exception.getHttpStatus().value())
                .statusName(exception.getHttpStatus().name())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknownException(
            final Exception exception,
            final HttpServletRequest request,
            final ApplicationException applicationException
    ) {
        var response = ApiErrorResponse.builder()
                .errorCode(ErrorCodes.EMPTY_MODEL_STACK)
                .message("Internal server error")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .statusName(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handeException(
            final HttpServletRequest request,
            final AuthenticationException exception){

        var response = ApiErrorResponse.builder()
                .errorCode(exception.getMessage())
                .message(exception.getMessage())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .statusName(HttpStatus.UNAUTHORIZED.name())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handeException(IllegalStateException exception){
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handeException(){
        return ResponseEntity
                .notFound()
                .build();
    }

    @ExceptionHandler(RequestNotValidException.class)
    public ResponseEntity<?> handeException(RequestNotValidException exception){
        return ResponseEntity
                .badRequest()
                .body(exception.getErrors());
    }
}
