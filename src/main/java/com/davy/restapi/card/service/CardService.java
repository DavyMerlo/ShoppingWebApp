package com.davy.restapi.card.service;

import com.davy.restapi.card.dto.CardDetail;
import com.davy.restapi.card.dto.CardDto;
import com.davy.restapi.card.request.CardRequest;

import java.util.List;

public interface CardService {
    List<CardDto> findAll();
    CardDetail findById(Long id);
    CardDetail save(CardRequest request);
    void updateById(Long id, CardRequest request);
}
