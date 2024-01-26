package com.davy.restapi.card.repository;

import com.davy.restapi.card.entity.CustomerCard;

import java.util.List;
import java.util.Optional;

public interface CustomCardRepository {
    List<CustomerCard> getAllCustomerCards();
    Optional<CustomerCard> getCustomerCardById(Long id);
    void saveCustomerCard(CustomerCard customerCard);
    void updateCustomerCard(CustomerCard customerCard);
    void removeCustomer(CustomerCard customerCard);
}
