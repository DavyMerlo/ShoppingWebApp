package com.davy.restapi.shared.handler;

import ch.qos.logback.core.spi.ErrorCodes;
import com.davy.restapi.shared.exceptions.RequestNotValidException;
import com.davy.restapi.shared.response.ApiErrorResponse;
import com.davy.restapi.shared.exceptions.ApplicationException;
import com.davy.restapi.shared.response.PassWordErrorResponse;
import com.davy.restapi.shared.response.UnauthorizedResponse;
import com.davy.restapi.shared.response.ValidationErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .statusName(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handeAuthenticationException(
            final HttpServletRequest request,
            final AuthenticationException exception){

        var response = UnauthorizedResponse.builder()
                .message(HttpStatus.UNAUTHORIZED.name())
                .status(HttpStatus.UNAUTHORIZED.value())
                .url(request.getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleException(IllegalStateException exception){
        var response = PassWordErrorResponse.builder()
                .Error(exception.getMessage())
                .message(false)
                .status((short) HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestNotValidException.class)
    public ResponseEntity<?> handleException(RequestNotValidException exception){

        var response = ValidationErrorResponse.builder()
                .validationErrors(exception.getValidationErrors())
                .message(false)
                .status((short) HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
