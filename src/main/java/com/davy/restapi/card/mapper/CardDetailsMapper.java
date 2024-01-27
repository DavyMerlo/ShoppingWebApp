package com.davy.restapi.card.mapper;

import com.davy.restapi.card.dto.CardDetails;
import com.davy.restapi.card.entity.CustomerCard;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class CardDetailsMapper implements Function<CustomerCard, CardDetails> {

    @Override
    public CardDetails apply(CustomerCard customerCard) {
        return new CardDetails(
                customerCard.getId(),
                customerCard.getNumber(),
                customerCard.getPoints()
        );
    };
}

