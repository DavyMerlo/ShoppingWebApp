package com.davy.restapi.card.mapper;

import com.davy.restapi.card.dto.CardDetailDTO;
import com.davy.restapi.card.dto.CardDTO;
import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.card.dto.CardRequestDTO;
import com.davy.restapi.shared.mapper.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class CardMapper implements ObjectMapper<CardRequestDTO, CustomerCard> {

    @Override
    public CustomerCard mapSourceToDestination(CardRequestDTO source, CustomerCard destination) {
        destination.setPoints(source.getPoints());
        destination.setNumber(source.getNumber());
        return destination;
    }

    @Override
    public CardDetailDTO mapToDetailsDto(CustomerCard customerCard) {
        return new CardDetailDTO(
                customerCard.getId(),
                customerCard.getNumber(),
                customerCard.getPoints()
        );
    }

    @Override
    public CardDTO mapToDto(CustomerCard customerCard) {
        return new CardDTO(
                customerCard.getId(),
                customerCard.getNumber()
        );
    }

    @Override
    public CustomerCard mapToEntity(CardRequestDTO cardRequestDTO) {
        if (cardRequestDTO == null) {
            return null;
        }
        return CustomerCard.builder()
                .points(cardRequestDTO.getPoints())
                .number(cardRequestDTO.getNumber())
                .build();
    }

    @Override
    public Object mapToListDto(CustomerCard entity) {
        return null;
    }
}