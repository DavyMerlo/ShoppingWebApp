package com.davy.restapi.shared.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnauthorizedResponse {

    private final String message;
    private final Integer status;
    private final String url;
}
