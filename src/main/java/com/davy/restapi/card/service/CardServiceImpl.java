package com.davy.restapi.card.service;

import com.davy.restapi.card.dto.CardDetail;
import com.davy.restapi.card.dto.CardDto;
import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.card.request.CardRequest;
import com.davy.restapi.shared.mapper.ResponseMapper;
import com.davy.restapi.shared.repository.GenericCrudRepository;
import com.davy.restapi.shared.service.GenericCrudServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CardServiceImpl extends GenericCrudServiceImpl<CustomerCard, CardRequest>
        implements CardService {

    public CardServiceImpl(GenericCrudRepository<CustomerCard> repository,
                           ResponseMapper<CardRequest, CustomerCard> responseMapper) {
        super(repository, responseMapper);
    }

    @Override
    public List<CardDto> findAll() {
        return (List<CardDto>) super.findAll();
    }

    @Override
    public CardDetail findById(Long id) {
        return (CardDetail) super.findById(id);
    }

    @Override
    public CardDetail save(CardRequest createRequest) {
        return (CardDetail) super.save(createRequest);
    }

    @Override
    public void updateById(Long id, CardRequest request) {
        super.updateById(id, request);
    }
}