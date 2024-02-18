package com.davy.restapi.customercard.repository;

import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.repository.CrudRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class CustomerCardRepositoryTest extends TestContainer {

    @Autowired
    private CrudRepository<CustomerCard> cardRepository;

    @Test
    @Order(1)
    void shouldGetAllCards(){
        var cards = cardRepository.getAll();
        assertThat(cards).hasSize(1);
    }

    @Test
    @Order(2)
    void shouldGetCustomerCardById(){
        var customerCard = cardRepository.getById(1L);
        assertThat(customerCard.get().getPoints()).isEqualTo((byte) 10);
        assertThat(customerCard.get().getNumber()).isEqualTo("15151515151515");
    }


}
