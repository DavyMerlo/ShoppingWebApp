package com.davy.restapi.shared.response;

import com.davy.restapi.shared.dto.ValidationErrorItems;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Set;

@Data
@Getter
@Builder
public class ValidationErrorResponse {

    @JsonProperty("errors")
    private Set<ValidationErrorItems> validationErrors;

    @JsonProperty("message")
    private Boolean message;

    @JsonProperty("status")
    private Short status;
}
