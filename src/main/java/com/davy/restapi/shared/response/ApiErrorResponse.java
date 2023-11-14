package com.davy.restapi.shared.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiErrorResponse {

    private final String errorCode;
    private final String message;
    private final Integer statusCode;
    private final String statusName;
    private final String path;
    private final String method;
    private final LocalDateTime timestamp;
}
