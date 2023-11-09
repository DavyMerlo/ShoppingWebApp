package com.davy.restapi.shared.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class FieldErrorResponse {

    @JsonProperty("fieldErrors")
    private Set<String> fieldErrors;
}
