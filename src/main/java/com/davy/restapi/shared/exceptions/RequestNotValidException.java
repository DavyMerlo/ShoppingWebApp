package com.davy.restapi.shared.exceptions;

import com.davy.restapi.shared.dto.ValidationErrorItems;
import com.davy.restapi.shared.response.ValidationErrorResponse;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class RequestNotValidException extends RuntimeException{

    private Set<ValidationErrorItems> validationErrors;
}
