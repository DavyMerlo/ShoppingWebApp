package com.davy.restapi.address.repository;

import com.davy.restapi.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long>,
        CustomAddressRepository {
}
