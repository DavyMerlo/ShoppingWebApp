package com.davy.restapi.customercard.data;

import com.davy.restapi.card.dto.CardDetailDTO;
import com.davy.restapi.shared.utils.ExpectedDataProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomerCardData implements ExpectedDataProvider<CardDetailDTO> {

    @Override
    public CardDetailDTO getObject() {
        return new CardDetailDTO(
                1L,
                "15151515151515",
                (byte) 10
        );
    }

    @Override
    public CardDetailDTO getSavedObject() {
        return new CardDetailDTO(
                2L,
                "111111111111",
                (byte) 20
        );
    }

    @Override
    public CardDetailDTO getUpdatedObject() {
        return new CardDetailDTO(
                1L,
                "222222222222",
                (byte) 25
        );
    }
}
