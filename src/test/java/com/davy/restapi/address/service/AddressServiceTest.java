package com.davy.restapi.address.service;

import com.davy.restapi.address.dto.AddressDetail;
import com.davy.restapi.address.dto.AddressDto;
import com.davy.restapi.address.entity.Address;
import com.davy.restapi.address.dto.AddressRequest;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.service.CrudService;
import com.davy.restapi.shared.service.CrudServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddressServiceTest extends TestContainer {

    @Autowired
    private CrudService<Address, AddressRequest> addressService;

    @Test
    @Order(1)
    void shouldGetAllAddresses(){
        List<AddressDto> addresses = (List<AddressDto>) addressService.findAll();
        assertThat(addresses).hasSize(1);
    }

    @Test
    @Order(2)
    void shouldGetAddressById(){
        AddressDetail address = (AddressDetail) addressService.findById(1L);
        assertThat(address.street().equals("Rootstraat"));
        assertThat(address.houseNumber().equals("30"));
        assertThat(address.busNumber().equals("1"));
        assertThat(address.localAuthority().equals("Maasmechelen"));
        assertThat(address.postalCode().equals("3630"));
    }

    @Test
    @Order(3)
    @Transactional
    void shouldSaveAddress() throws IOException {

        Optional<AddressRequest> request = Optional.of(new AddressRequest(
                "Teststraat",
                "50",
                "2",
                "3600",
                "Testgemeente"
        ));

        addressService.save(request.get());
        AddressDetail savedAddress = (AddressDetail) addressService.findById(2L);
        assertNotNull(savedAddress);
        assertEquals("Teststraat", savedAddress.street());
        assertEquals("50", savedAddress.houseNumber());
        assertEquals("2", savedAddress.busNumber());
        assertEquals("Testgemeente", savedAddress.localAuthority());
        assertEquals("3600", savedAddress.postalCode());
    }

    @Test
    @Order(4)
    void shouldUpdateAddress(){

        Optional<AddressRequest> request = Optional.of(new AddressRequest(
                "Teststraat 2",
                "30",
                "1",
                "3630",
                "Testgemeente_2"
        ));

        addressService.updateById(1L, request.get());
        AddressDetail updatedAddress = (AddressDetail) addressService.findById(1L);
        assertNotNull(updatedAddress);
        assertEquals("Teststraat 2", updatedAddress.street());
        assertEquals("30", updatedAddress.houseNumber());
        assertEquals("1", updatedAddress.busNumber());
        assertEquals("3630", updatedAddress.postalCode());
        assertEquals("Testgemeente_2", updatedAddress.localAuthority());
    }
}
