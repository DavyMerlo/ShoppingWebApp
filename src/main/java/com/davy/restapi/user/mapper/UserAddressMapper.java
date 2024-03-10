package com.davy.restapi.user.mapper;

import com.davy.restapi.address.dto.AddressDTO;
import com.davy.restapi.user.entity.UserEntity;
import com.davy.restapi.user.dto.UserAddress;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class UserAddressMapper implements Function<UserEntity, UserAddress> {

    @Override
    public UserAddress apply(UserEntity user) {
        return new UserAddress(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                new AddressDTO(
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
