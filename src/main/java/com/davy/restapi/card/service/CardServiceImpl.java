package com.davy.restapi.card.service;

import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.card.dto.CardRequestDTO;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl extends CrudServiceImpl<CustomerCard, CardRequestDTO>
        implements CardService {

    public CardServiceImpl(CrudRepository<CustomerCard> repository,
                           ObjectMapper<CardRequestDTO, CustomerCard> objectMapper) {
        super(repository, objectMapper);
    }
}