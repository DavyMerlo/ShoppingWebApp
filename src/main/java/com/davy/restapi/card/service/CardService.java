package com.davy.restapi.card.service;

import com.davy.restapi.card.dto.CardRequestDTO;
import com.davy.restapi.card.entity.CustomerCardEntity;
import com.davy.restapi.shared.service.CrudService;

public interface CardService extends CrudService<CustomerCardEntity, CardRequestDTO> {
}
