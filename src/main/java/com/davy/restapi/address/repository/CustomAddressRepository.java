package com.davy.restapi.address.repository;

import com.davy.restapi.address.entity.Address;

import java.util.List;
import java.util.Optional;

public interface CustomAddressRepository {
    List<Address> getAllAddresses();
    Optional<Address> getAddressById(Long id);
    Long saveAddress(Address address);
    void updateAddress(Address address);
    void removeAddress(Address address);
}
