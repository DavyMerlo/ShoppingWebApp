package com.davy.restapi.customercard.mapper;

import com.davy.restapi.card.dto.CardDetailDTO;
import com.davy.restapi.shared.utils.JSONToObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JSONToCustomerCard implements JSONToObject<CardDetailDTO> {

    @Override
    public CardDetailDTO mapJSONResponseToObject(JSONObject response)
            throws JSONException {
        var result = response.getJSONObject("result");
        var card = result.getJSONObject("card");
        var cardId = card.getLong("id");
        var cardNumber = card.getString("number");
        var cardPoints = card.getInt("points");
        return new CardDetailDTO(
                cardId,
                cardNumber,
                (byte) cardPoints
        );
    }
}
