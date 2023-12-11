package com.davy.restapi.shared.exceptions;

import com.davy.restapi.shared.dto.ValidationErrorItems;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class RequestNotValidException extends RuntimeException{

    private Set<ValidationErrorItems> validationErrors;
}
