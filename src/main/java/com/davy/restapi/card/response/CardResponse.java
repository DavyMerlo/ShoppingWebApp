package com.davy.restapi.card.response;

import com.davy.restapi.card.dto.CardItems;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardResponse {

    @JsonProperty("card")
    public CardItems card;
}
