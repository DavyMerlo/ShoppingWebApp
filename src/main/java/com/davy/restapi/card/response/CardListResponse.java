package com.davy.restapi.card.response;


import com.davy.restapi.card.dto.CardDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CardListResponse {
    @JsonProperty("cards")
    public List<CardDto> cards;
    {
        cards = new ArrayList<>();
    }
}
