package com.davy.restapi.card.response;

import com.davy.restapi.card.dto.CardDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardResponse {

//    @JsonProperty("card")
//    public CardItems card;

    @JsonProperty("card")
    public CardDetails card;
}
