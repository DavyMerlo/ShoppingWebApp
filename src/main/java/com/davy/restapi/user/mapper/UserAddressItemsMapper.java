package com.davy.restapi.user.mapper;

import com.davy.restapi.address.dto.Address;
import com.davy.restapi.user.entity.User;
import com.davy.restapi.user.dto.UserAddressItems;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class UserAddressItemsMapper implements Function<User, UserAddressItems> {

    @Override
    public UserAddressItems apply(User user) {
        return new UserAddressItems(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                new Address(
                        user.getAddress().getId(),
                        user.getAddress().getStreet(),
                        user.getAddress().getHouseNumber(),
                        user.getAddress().getBusNumber(),
                        user.getAddress().getPostalCode(),
                        user.getAddress().getLocalAuthority()
                )
        );
    }
}
