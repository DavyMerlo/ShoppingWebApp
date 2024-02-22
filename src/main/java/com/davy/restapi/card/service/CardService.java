package com.davy.restapi.card.service;

import com.davy.restapi.card.dto.CardRequestDTO;
import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.shared.service.CrudService;

public interface CardService extends CrudService<CustomerCard, CardRequestDTO> {
}
