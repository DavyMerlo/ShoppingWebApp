package com.davy.restapi.customercard;

import com.davy.restapi.card.dto.CardDetailDTO;
import com.davy.restapi.card.dto.CardDTO;
import com.davy.restapi.card.dto.CardRequestDTO;
import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.service.CrudService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerCardServiceTest extends TestContainer {

    @Autowired
    private CrudService<CustomerCard, CardRequestDTO> cardService;

    @Test
    @Order(1)
    void shouldGetAllCustomerCards(){
        List<CardDTO> cards = (List<CardDTO>) cardService.findAll();
        assertThat(cards).hasSize(1);
    }

    @Test
    @Order(2)
    void shouldGetCustomerCardById(){
        CardDetailDTO card = (CardDetailDTO) cardService.findById(1L);
        assertThat(card.points()).isEqualTo((byte) 10);
        assertThat(card.number()).isEqualTo("15151515151515");
    }

    @Test
    @Order(3)
    @Transactional
    void shouldSaveCustomerCard() throws IOException {

        Optional<CardRequestDTO> request = Optional.of(new CardRequestDTO(
                "151515151515",
                (byte) 20
        ));

        cardService.save(request.get());
        CardDetailDTO savedCard = (CardDetailDTO) cardService.findById(2L);
        assertNotNull(savedCard);
        assertEquals("151515151515", savedCard.number());
        assertEquals((byte) 20, savedCard.points());
    }

    @Test
    @Order(4)
    void shouldUpdateCustomerCard() {
        Optional<CardRequestDTO> request = Optional.of(new CardRequestDTO(
                "151515151515",
                (byte) 20
        ));

        cardService.updateById(1L, request.get());
        CardDetailDTO updatedCard = (CardDetailDTO) cardService.findById(1L);
        assertNotNull(updatedCard);
        assertEquals("151515151515", updatedCard.number());
        assertEquals((byte) 20, updatedCard.points());
    }
}
