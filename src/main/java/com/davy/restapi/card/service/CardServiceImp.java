package com.davy.restapi.card.service;

import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.card.repository.CardRepository;
import com.davy.restapi.card.mapper.CardMapper;
import com.davy.restapi.card.request.CardCreateRequest;
import com.davy.restapi.card.request.CardUpdateRequest;
import com.davy.restapi.card.response.CardListResponse;
import com.davy.restapi.card.response.CardResponse;
import com.davy.restapi.shared.exceptions.ThrowException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardServiceImp implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public CardResponse findCardById(Long id) {
        var response = new CardResponse();
        if(cardRepository.getCustomerCardById(id).isEmpty()){
            ThrowException.objectByIdException(id, "Card");
        }
        response.setCard(cardRepository.getCustomerCardById(id)
                .stream()
                .map(cardMapper)
                .findFirst()
                .get());
        return response;
    }

    @Override
    public CardListResponse findAllCards() {
        var response = new CardListResponse();
        if(cardRepository.getAllCustomerCards().isEmpty()){
            ThrowException.objectException("Cards");
        }
        response.setCards(cardRepository.getAllCustomerCards()
                .stream()
                .map(cardMapper)
                .collect(Collectors.toList()));
        return response;
    }

    @Override
    public CardListResponse saveCard(CardCreateRequest request) {
        var card = CustomerCard.builder()
                .number(request.getNumber())
                .points(request.getPoints())
                .build();
        cardRepository.saveCustomerCard(card);
        return this.findAllCards();
    }

    @Override
    public CardResponse updateCardById(Long id, CardUpdateRequest request) {
        var card = cardRepository.getCustomerCardById(id);
        if(card.isEmpty()){
            ThrowException.objectByIdException(id, "Card");
        }
        card.get().setNumber(request.getNumber());
        card.get().setPoints(request.getPoints());
        cardRepository.updateCustomerCard(card.get());
        return this.findCardById(card.get().getId());
    }
}