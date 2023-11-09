package com.davy.restapi.address.repository;

import com.davy.restapi.address.entity.Address;
import com.davy.restapi.product.repository.CustomProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long>,
        CustomAddressRepository {
}
