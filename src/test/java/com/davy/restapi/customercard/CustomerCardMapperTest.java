package com.davy.restapi.customercard;

import com.davy.restapi.card.entity.CustomerCardEntity;
import com.davy.restapi.card.mapper.CardMapper;
import com.davy.restapi.card.dto.CardRequestDTO;
import com.davy.restapi.shared.mapper.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerCardMapperTest {

    private ObjectMapper<CardRequestDTO, CustomerCardEntity> customerCardMapper;
    private CardRequestDTO source;
    private CustomerCardEntity destination;

    @BeforeEach
    public void setUp() {
        customerCardMapper = new CardMapper();
        source = mock(CardRequestDTO.class);
        destination = new CustomerCardEntity();
    }

    @Test
    public void shouldMapCustomerCardRequestToCustomerCard() {
        when(source.getPoints()).thenReturn((byte) 100);
        when(source.getNumber()).thenReturn("123456789");

        CustomerCardEntity result = (CustomerCardEntity) customerCardMapper.mapSourceToDestination(source, destination);

        assertEquals(100, result.getPoints());
        assertEquals("123456789", result.getNumber());
    }
}
