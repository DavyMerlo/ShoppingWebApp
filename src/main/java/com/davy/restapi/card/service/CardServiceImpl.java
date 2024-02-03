package com.davy.restapi.card.service;


import com.davy.restapi.card.dto.CardDetail;
import com.davy.restapi.card.dto.CardDto;
import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.card.request.CardRequest;
import com.davy.restapi.card.response.CardListResponse;
import com.davy.restapi.card.response.CardResponse;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.AbstractCrudRepository;
import com.davy.restapi.shared.service.AbstractCrudService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CardServiceImpl extends AbstractCrudService<CustomerCard, CardRequest>
        implements CardService {

    public CardServiceImpl(AbstractCrudRepository<CustomerCard> repository,
                           ObjectMapper<CardRequest, CustomerCard> objectMapper) {
        super(repository, objectMapper);
    }

    @Override
    public CardListResponse findAll() {
        var response = new CardListResponse();
        var cards = super.findAll();
        response.setCards((List<CardDto>) cards);
        System.out.println(response.getCards());
        return response;
    }

    @Override
    public CardResponse findById(Long id) {
        var response = new CardResponse();
        var card = super.findById(id);
        response.setCard((CardDetail) card);
        return response;
    }

    @Override
    public CardResponse save(CardRequest createRequest) {
        var response = new CardResponse();
        var card = super.save(createRequest);
        response.setCard((CardDetail) card);
        return response;
    }

    @Override
    public void updateById(Long id, CardRequest request) {
        super.updateById(id, request);
    }
}