package com.davy.restapi.card.mapper;

import com.davy.restapi.card.dto.CardDetailDTO;
import com.davy.restapi.card.dto.CardDTO;
import com.davy.restapi.card.entity.CustomerCardEntity;
import com.davy.restapi.card.dto.CardRequestDTO;
import com.davy.restapi.shared.mapper.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class CardMapper implements ObjectMapper<CardRequestDTO, CustomerCardEntity> {

    @Override
    public CustomerCardEntity mapSourceToDestination(CardRequestDTO source, CustomerCardEntity destination) {
        destination.setPoints(source.getPoints());
        destination.setNumber(source.getNumber());
        return destination;
    }

    @Override
    public CardDetailDTO mapToDetailsDto(CustomerCardEntity customerCard) {
        return new CardDetailDTO(
                customerCard.getId(),
                customerCard.getNumber(),
                customerCard.getPoints()
        );
    }

    @Override
    public CardDTO mapToDto(CustomerCardEntity customerCard) {
        return new CardDTO(
                customerCard.getId(),
                customerCard.getNumber()
        );
    }

    @Override
    public CustomerCardEntity mapToEntity(CardRequestDTO cardRequestDTO) {
        if (cardRequestDTO == null) {
            return null;
        }
        return CustomerCardEntity.builder()
                .points(cardRequestDTO.getPoints())
                .number(cardRequestDTO.getNumber())
                .build();
    }

    @Override
    public Object mapToListDto(CustomerCardEntity entity) {
        return null;
    }
}