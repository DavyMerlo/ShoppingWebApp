package com.davy.restapi.address.mapper;

import com.davy.restapi.address.dto.AddressDetail;
import com.davy.restapi.address.dto.AddressDto;
import com.davy.restapi.address.entity.Address;
import com.davy.restapi.address.request.AddressRequest;
import com.davy.restapi.shared.mapper.ResponseMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper implements ResponseMapper<AddressRequest, Address> {

    @Override
    public Address mapSourceToDestination(AddressRequest source, Address destination) {
        destination.setStreet(source.getStreet());
        destination.setHouseNumber(source.getHouseNumber());
        destination.setBusNumber(source.getBusNumber());
        destination.setPostalCode(source.getPostalCode());
        destination.setLocalAuthority(source.getLocalAuthority());
        return destination;
    }

    @Override
    public AddressDto mapToDto(Address address) {
        return new AddressDto(
                address.getId(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getBusNumber(),
                address.getPostalCode(),
                address.getLocalAuthority()
        );
    }

    @Override
    public AddressDetail mapToDetails(Address address) {
        return new AddressDetail(
                address.getId(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getBusNumber(),
                address.getPostalCode(),
                address.getLocalAuthority()
        );
    }

    @Override
    public Address mapToEntity(AddressRequest addressRequest) {
        if (addressRequest == null) {
            return null;
        }
        return Address.builder()
                .street(addressRequest.getStreet())
                .houseNumber(addressRequest.getHouseNumber())
                .busNumber(addressRequest.getBusNumber())
                .localAuthority(addressRequest.getLocalAuthority())
                .postalCode(addressRequest.getPostalCode())
                .build();
    }
}