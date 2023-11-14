package com.davy.restapi.shared.validators;

import com.davy.restapi.shared.exceptions.RequestNotValidException;
import com.davy.restapi.shared.mapper.ValidationMapper;
import com.davy.restapi.shared.response.ValidationErrorResponse;
import jakarta.validation.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
@AllArgsConstructor
@Component
public class RequestValidator<T> {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    private final ValidationMapper validationMapper;

    public void validate (T request){
        Set<ConstraintViolation<T>> violations = validator.validate(request);
        boolean requestHasViolations = !violations.isEmpty();
        if(requestHasViolations){
            var errorsMessages = violations
                    .stream()
                    .map(validationMapper)
                    .collect(Collectors.toSet());
            ValidationErrorResponse response = new ValidationErrorResponse();
            response.setValidationErrors(errorsMessages);
            throw new RequestNotValidException(response);
        }
    }
}
