package com.davy.restapi.address;

import com.davy.restapi.address.entity.Address;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.repository.CrudRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddressRepositoryTest extends TestContainer {

    @Autowired
    private CrudRepository<Address> addressRepository;

    @DisplayName("Get all addresses")
    @Test
    @Order(1)
    void shouldGetAllAddresses(){
        var addresses = addressRepository.getAll();
        assertThat(addresses).hasSize(1);
    }

    @DisplayName("Get address by id")
    @Test
    @Order(2)
    void shouldGetAddressById(){
        var address_1 = addressRepository.getById(1L);
        assertThat(address_1.get().getStreet()).isEqualTo("Rootstraat");
        assertThat(address_1.get().getHouseNumber()).isEqualTo("30");
        assertThat(address_1.get().getBusNumber()).isEqualTo("1");
        assertThat(address_1.get().getLocalAuthority()).isEqualTo("Maasmechelen");
        assertThat(address_1.get().getPostalCode()).isEqualTo("3630");
    }

    @DisplayName("Save new address")
    @Test
    @Order(3)
    void shouldSaveAddress(){
        var address = Address.builder()
                .street("Teststraat")
                .houseNumber("50")
                .busNumber("2")
                .localAuthority("Testgemeente")
                .postalCode("3600")
                .build();

        addressRepository.save(address);
        var savedAddress = addressRepository.getById(2L);
        assertNotNull(savedAddress);
        assertEquals("Teststraat", savedAddress.get().getStreet());
        assertEquals("50", savedAddress.get().getHouseNumber());
        assertEquals("2", savedAddress.get().getBusNumber());
        assertEquals("Testgemeente", savedAddress.get().getLocalAuthority());
        assertEquals("3600", savedAddress.get().getPostalCode());
    }

    @DisplayName("Update address")
    @Test
    @Order(4)
    void shouldUpdateAddress(){
        var address = addressRepository.getById(1L);

        address = java.util.Optional.ofNullable(Address.builder()
                .id(1L)
                .street("Teststraat")
                .houseNumber("50")
                .busNumber("2")
                .localAuthority("Testgemeente")
                .postalCode("3600")
                .build());

        addressRepository.update(address.get());
        var updatedAddress = addressRepository.getById(1L);
        assertNotNull(updatedAddress);
        assertEquals("Teststraat", updatedAddress.get().getStreet());
        assertEquals("50", updatedAddress.get().getHouseNumber());
        assertEquals("2", updatedAddress.get().getBusNumber());
        assertEquals("Testgemeente", updatedAddress.get().getLocalAuthority());
        assertEquals("3600", updatedAddress.get().getPostalCode());
    }
}
