package com.davy.restapi.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CardItems {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("number")
    private String number;

    @JsonProperty("points")
    private byte points;

    public CardItems(Long id,
                     String number,
                     byte points) {
        this.id = id;
        this.number = number;
        this.points = points;
    }
}
