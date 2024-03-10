package com.davy.restapi.address.repository;

import com.davy.restapi.address.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Long>,
        CustomAddressRepository {
}
