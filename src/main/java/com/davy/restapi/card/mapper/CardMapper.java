package com.davy.restapi.card.mapper;

import com.davy.restapi.card.dto.Card;
import com.davy.restapi.card.entity.CustomerCard;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CardMapper implements Function<CustomerCard, Card> {

    @Override
    public Card apply(CustomerCard customerCard) {
        return new Card(
                customerCard.getId(),
                customerCard.getNumber());
    }
}