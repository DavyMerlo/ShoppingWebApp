package com.davy.restapi.shared.exceptions;

import com.davy.restapi.shared.response.ValidationErrorResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class RequestNotValidException extends RuntimeException{

    private final ValidationErrorResponse errors;
}
