package com.davy.restapi.shared.validators;

import com.davy.restapi.shared.dto.ValidationErrorItems;
import com.davy.restapi.shared.exceptions.RequestNotValidException;
import com.davy.restapi.shared.mapper.ValidationMapper;
import jakarta.validation.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class RequestValidatorImpl<T> implements RequestValidator<T>{

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private final Validator validator = factory.getValidator();

    private final ValidationMapper validationMapper;

    private final Set<ValidationErrorItems> validationErrors;

    @Override
    public void validate(T request) {
        validationErrors.clear();
        Set<ConstraintViolation<T>> violations = validator.validate(request);
        boolean requestHasViolations = !violations.isEmpty();
        if(requestHasViolations){
            var errorsMessages = violations
                    .stream()
                    .map(validationMapper)
                    .collect(Collectors.toSet());
            validationErrors.addAll(errorsMessages);
            throw new RequestNotValidException(validationErrors);
        }
    }
}
