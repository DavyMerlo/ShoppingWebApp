package com.davy.restapi.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


public record CardDto(
        @JsonProperty("id") Long id,
        @JsonProperty("number") String number) {
}