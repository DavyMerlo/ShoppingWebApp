package com.davy.restapi.shared.mapper;

import com.davy.restapi.shared.dto.ValidationErrorItems;
import jakarta.validation.ConstraintViolation;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class ValidationMapper implements Function<ConstraintViolation, ValidationErrorItems> {

    @Override
    public ValidationErrorItems apply(ConstraintViolation violation) {
        return new ValidationErrorItems(
                violation.getPropertyPath().toString(),
                violation.getMessage()
        );
    }
}
