package com.davy.restapi.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CardRequest {

    @JsonProperty("number")
    private String number;

    @JsonProperty("points")
    private byte points;

    public CardRequest(String number, byte points) {
        this.number = number;
        this.points = points;
    }
}
