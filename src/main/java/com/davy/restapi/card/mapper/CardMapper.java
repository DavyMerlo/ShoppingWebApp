package com.davy.restapi.card.mapper;

import com.davy.restapi.card.dto.CardDetail;
import com.davy.restapi.card.dto.CardDto;
import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.card.request.CardRequest;
import com.davy.restapi.shared.mapper.ResponseMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CardMapper implements ResponseMapper<CardRequest, CustomerCard> {

    @Override
    public CustomerCard mapSourceToDestination(CardRequest source, CustomerCard destination) {
        destination.setPoints(source.getPoints());
        destination.setNumber(source.getNumber());
        return destination;
    }

    @Override
    public CardDetail mapToDetails(CustomerCard customerCard) {
        return new CardDetail(
                customerCard.getId(),
                customerCard.getNumber(),
                customerCard.getPoints()
        );
    }

    @Override
    public CardDto mapToDto(CustomerCard customerCard) {
        return new CardDto(
                customerCard.getId(),
                customerCard.getNumber()
        );
    }

    @Override
    public CustomerCard mapToEntity(CardRequest cardRequest) {
        if (cardRequest == null) {
            return null;
        }
        return CustomerCard.builder()
                .points(cardRequest.getPoints())
                .number(cardRequest.getNumber())
                .build();
    }
}