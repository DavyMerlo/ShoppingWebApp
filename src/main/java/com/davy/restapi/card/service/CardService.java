package com.davy.restapi.card.service;

import com.davy.restapi.card.request.CardRequest;
import com.davy.restapi.card.response.CardListResponse;
import com.davy.restapi.card.response.CardResponse;

public interface CardService {
    CardListResponse findAll();
    CardResponse findById(Long id);
    CardResponse save(CardRequest request);
    void updateById(Long id, CardRequest request);
}
