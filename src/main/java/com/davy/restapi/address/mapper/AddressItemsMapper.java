package com.davy.restapi.address.mapper;

import com.davy.restapi.address.entity.Address;
import com.davy.restapi.address.dto.AddressItems;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AddressItemsMapper implements Function<Address, AddressItems> {

    @Override
    public AddressItems apply(Address address) {
        return new AddressItems(
                address.getId(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getBusNumber(),
                address.getPostalCode(),
                address.getLocalAuthority()
        );
    };
};
