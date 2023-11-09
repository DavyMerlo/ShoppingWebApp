package com.davy.restapi.card.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CardCreateRequest {

    @JsonProperty("number")
    private String number;

    @JsonProperty("points")
    private byte points;
}
