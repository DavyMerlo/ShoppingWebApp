package com.davy.restapi.shared.validators;

import com.davy.restapi.address.request.AddressCreateRequest;
import com.davy.restapi.shared.exceptions.ApplicationException;
import com.davy.restapi.shared.exceptions.RequestNotValidException;
import com.davy.restapi.shared.response.FieldErrorResponse;
import jakarta.validation.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RequestValidator<T> {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validate (T request){
        Set<ConstraintViolation<T>> violations = validator.validate(request);
        boolean requestHasViolations = !violations.isEmpty();
        if(requestHasViolations){
            Set<String> errors = new HashSet<>();
            var errorsMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());

            FieldErrorResponse response = new FieldErrorResponse();
            response.setFieldErrors(errorsMessages);
            throw new RequestNotValidException(response);
        }
    }
}
