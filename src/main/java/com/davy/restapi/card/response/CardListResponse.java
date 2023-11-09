package com.davy.restapi.card.response;

import com.davy.restapi.address.dto.AddressItems;
import com.davy.restapi.card.dto.CardItems;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CardListResponse {
    @JsonProperty("cards")
    public List<CardItems> cards;
    {
        cards = new ArrayList<>();
    }
}
