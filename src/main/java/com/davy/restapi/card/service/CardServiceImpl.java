package com.davy.restapi.card.service;

import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.card.dto.CardRequest;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl extends CrudServiceImpl<CustomerCard, CardRequest>
        implements CardService {

    public CardServiceImpl(CrudRepository<CustomerCard> repository,
                           ObjectMapper<CardRequest, CustomerCard> objectMapper) {
        super(repository, objectMapper);
    }
}