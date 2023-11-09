package com.davy.restapi.shared.exceptions;

import com.davy.restapi.shared.response.FieldErrorResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.ErrorResponse;

import java.util.Set;

@Data
public class RequestNotValidException extends RuntimeException{

//    @JsonProperty("errors")
//    private final Set<String> errors;

    private final FieldErrorResponse errors;
}
