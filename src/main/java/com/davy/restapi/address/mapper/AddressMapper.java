package com.davy.restapi.address.mapper;

import com.davy.restapi.address.dto.Address;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AddressMapper implements Function<com.davy.restapi.address.entity.Address, Address> {

    @Override
    public Address apply(com.davy.restapi.address.entity.Address address) {
        return new Address(
                address.getId(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getBusNumber(),
                address.getPostalCode(),
                address.getLocalAuthority()
        );
    };
};
