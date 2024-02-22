package com.davy.restapi.address.mapper;

import com.davy.restapi.address.dto.AddressDetailDTO;
import com.davy.restapi.address.dto.AddressDTO;
import com.davy.restapi.address.entity.Address;
import com.davy.restapi.address.dto.AddressRequestDTO;
import com.davy.restapi.shared.mapper.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper implements ObjectMapper<AddressRequestDTO, Address> {

    @Override
    public Address mapSourceToDestination(AddressRequestDTO source, Address destination) {
        destination.setStreet(source.getStreet());
        destination.setHouseNumber(source.getHouseNumber());
        destination.setBusNumber(source.getBusNumber());
        destination.setPostalCode(source.getPostalCode());
        destination.setLocalAuthority(source.getLocalAuthority());
        return destination;
    }

    @Override
    public AddressDTO mapToDto(Address address) {
        return new AddressDTO(
                address.getId(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getBusNumber(),
                address.getPostalCode(),
                address.getLocalAuthority()
        );
    }

    @Override
    public AddressDetailDTO mapToDetailsDto(Address address) {
        return new AddressDetailDTO(
                address.getId(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getBusNumber(),
                address.getPostalCode(),
                address.getLocalAuthority()
        );
    }

    @Override
    public Address mapToEntity(AddressRequestDTO addressRequestDTO) {
        if (addressRequestDTO == null) {
            return null;
        }
        return Address.builder()
                .street(addressRequestDTO.getStreet())
                .houseNumber(addressRequestDTO.getHouseNumber())
                .busNumber(addressRequestDTO.getBusNumber())
                .localAuthority(addressRequestDTO.getLocalAuthority())
                .postalCode(addressRequestDTO.getPostalCode())
                .build();
    }

    @Override
    public Object mapToListDto(Address entity) {
        return null;
    }
}