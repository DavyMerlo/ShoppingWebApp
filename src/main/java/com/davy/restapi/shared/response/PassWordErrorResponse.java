package com.davy.restapi.shared.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;


@Data
@Getter
@Builder
public class PassWordErrorResponse {

    @JsonProperty("error")
    public Object Error;

    @JsonProperty("message")
    private Boolean message;

    @JsonProperty("status")
    private Short status;
}
