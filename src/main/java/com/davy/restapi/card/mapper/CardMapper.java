package com.davy.restapi.card.mapper;

import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.card.dto.CardItems;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CardMapper implements Function<CustomerCard, CardItems> {

    @Override
    public CardItems apply(CustomerCard customerCard) {
        return new CardItems(
                customerCard.getId(),
                customerCard.getNumber(),
                customerCard.getPoints()
        );
    }
}
