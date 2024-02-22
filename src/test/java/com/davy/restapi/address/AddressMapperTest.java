package com.davy.restapi.address;

import com.davy.restapi.address.dto.AddressDTO;
import com.davy.restapi.address.entity.Address;
import com.davy.restapi.address.dto.AddressRequestDTO;
import com.davy.restapi.address.mapper.AddressMapper;
import com.davy.restapi.shared.mapper.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressMapperTest {

    private ObjectMapper<AddressRequestDTO, Address> addressMapper;
    private AddressRequestDTO source;
    private AddressRequestDTO request;
    private Address destination;
    private Address address;

    @BeforeEach
    public void SetUp(){
        addressMapper = new AddressMapper();
        source = mock(AddressRequestDTO.class);
        destination = new Address();
        address = mock(Address.class);
        request = mock(AddressRequestDTO.class);
    }

    @Test
    public void shouldMapAddressRequestToAddress(){
        when(source.getStreet()).thenReturn("Rootstraat");
        when(source.getHouseNumber()).thenReturn("30");
        when(source.getBusNumber()).thenReturn("1");
        when(source.getPostalCode()).thenReturn("3630");
        when(source.getLocalAuthority()).thenReturn("Maasmechelen");

        Address result = (Address) addressMapper.mapSourceToDestination(source, destination);

        assertEquals("Rootstraat", result.getStreet());
        assertEquals("30", result.getHouseNumber());
        assertEquals("1", result.getBusNumber());
        assertEquals("3630", result.getPostalCode());
        assertEquals("Maasmechelen", result.getLocalAuthority());
    }

    @Test
    public void shouldMapAddressToAddressDto() {

        when(address.getId()).thenReturn(1L);
        when(address.getStreet()).thenReturn("Rootstraat");
        when(address.getHouseNumber()).thenReturn("30");
        when(address.getBusNumber()).thenReturn("1");
        when(address.getPostalCode()).thenReturn("3630");
        when(address.getLocalAuthority()).thenReturn("Maasmechelen");
        when(address.getCreatedAt()).thenReturn(LocalDateTime.now());

        AddressDTO result = (AddressDTO) addressMapper.mapToDto(address);

        assertEquals(1L, result.id());
        assertEquals("Rootstraat", result.street());
        assertEquals("30", result.houseNumber());
        assertEquals("1", result.busNumber());
        assertEquals("3630", result.postalCode());
        assertEquals("Maasmechelen", result.localAuthority());
    }

    @Test
    public void shouldMapAddressRequestToAddressWhenRequestIsNull(){
        when(request.getStreet()).thenReturn(null);
        when(request.getHouseNumber()).thenReturn(null);
        when(request.getBusNumber()).thenReturn(null);
        when(request.getPostalCode()).thenReturn(null);
        when(request.getLocalAuthority()).thenReturn(null);

        Address result = (Address) addressMapper.mapToEntity(request);
        assertNull(result.getStreet());
        assertNull(result.getLocalAuthority());
    }
}
