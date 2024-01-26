package com.davy.restapi.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CardItems(
        @JsonProperty("id") Long id,
        @JsonProperty("number") String number,
        @JsonProperty("points") byte points) {
}
