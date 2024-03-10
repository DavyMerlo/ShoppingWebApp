package com.davy.restapi.shared.validators;

public interface RequestValidator<T> {
    void validate (T request);
}
