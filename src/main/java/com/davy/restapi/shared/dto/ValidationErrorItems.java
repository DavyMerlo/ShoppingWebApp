package com.davy.restapi.shared.dto;

public record ValidationErrorItems(
        String field,
        String message
) {
}
