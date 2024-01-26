package com.davy.restapi.card.service;

import com.davy.restapi.card.request.CardCreateRequest;
import com.davy.restapi.card.request.CardUpdateRequest;
import com.davy.restapi.card.response.CardListResponse;
import com.davy.restapi.card.response.CardResponse;

public interface CardService {
    CardResponse findCardById(Long id);
    CardListResponse findAllCards();
    CardResponse saveCard(CardCreateRequest request);
    CardResponse updateCardById(Long id, CardUpdateRequest request);
}
