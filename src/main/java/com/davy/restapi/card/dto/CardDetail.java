package com.davy.restapi.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public record CardDetail(
        @JsonProperty("id") Long id,
        @JsonProperty("number") String number,
        @JsonProperty("points") byte points) {
}
