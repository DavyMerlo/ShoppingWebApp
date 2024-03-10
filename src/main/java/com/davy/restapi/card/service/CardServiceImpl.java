package com.davy.restapi.card.service;

import com.davy.restapi.card.entity.CustomerCardEntity;
import com.davy.restapi.card.dto.CardRequestDTO;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl extends CrudServiceImpl<CustomerCardEntity, CardRequestDTO>
        implements CardService {

    public CardServiceImpl(CrudRepository<CustomerCardEntity> repository,
                           ObjectMapper<CardRequestDTO, CustomerCardEntity> objectMapper) {
        super(repository, objectMapper);
    }
}