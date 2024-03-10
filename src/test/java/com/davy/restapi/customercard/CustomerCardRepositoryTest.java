package com.davy.restapi.customercard;

import com.davy.restapi.card.entity.CustomerCardEntity;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.repository.CrudRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerCardRepositoryTest extends TestContainer {

    @Autowired
    private CrudRepository<CustomerCardEntity> cardRepository;

    @DisplayName("Get all customer cards")
    @Test
    @Order(1)
    void shouldGetAllCards(){
        var cards = cardRepository.getAll();
        assertThat(cards).hasSize(1);
    }

    @DisplayName("Get customer card by id")
    @Test
    @Order(2)
    void shouldGetCustomerCardById(){
        var customerCard = cardRepository.getById(1L);
        assertThat(customerCard.get().getPoints()).isEqualTo((byte) 10);
        assertThat(customerCard.get().getNumber()).isEqualTo("15151515151515");
    }

    @DisplayName("Save customer card")
    @Test
    @Order(3)
    void shouldSaveCustomerCard(){
        var card = CustomerCardEntity.builder()
                .number("10101010101010")
                .points((byte) 15)
                .build();

        cardRepository.save(card);
        var savedCard = cardRepository.getById(2L);
        assertNotNull(savedCard);
        assertEquals("10101010101010", savedCard.get().getNumber());
        assertEquals((byte) 15, savedCard.get().getPoints());
    }

    @DisplayName("Update customer card")
    @Test
    @Order(4)
    void shouldUpdateCustomerCard(){
        var customerCard = cardRepository.getById(1L);

        customerCard = java.util.Optional.ofNullable(CustomerCardEntity.builder()
                .id(1L)
                .number("10101010101015")
                .points((byte) 20)
                .build());

        cardRepository.update(customerCard.get());
        var updatedCard = cardRepository.getById(1L);
        assertNotNull(updatedCard);
        assertEquals("10101010101015", updatedCard.get().getNumber());
        assertEquals( (byte) 20, updatedCard.get().getPoints());
    }
}
