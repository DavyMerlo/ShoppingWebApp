package com.davy.restapi.shared.response;

import com.davy.restapi.shared.dto.ValidationErrorItems;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class ValidationErrorResponse {

    @JsonProperty("errors")
    private Set<ValidationErrorItems> validationErrors;
}
