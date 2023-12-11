package com.davy.restapi.shared.validators;

import com.davy.restapi.user.entity.User;
import com.davy.restapi.user.request.ChangePasswordRequest;
import jakarta.validation.ConstraintViolation;

import java.security.Principal;
import java.util.Set;

public interface RequestValidator<T> {
    void validate (T request);
}
