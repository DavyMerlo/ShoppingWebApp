package com.davy.restapi.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CardDTO(
        @JsonProperty("id") Long id,
        @JsonProperty("number") String number) {
}