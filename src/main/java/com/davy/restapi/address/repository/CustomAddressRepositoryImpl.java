package com.davy.restapi.address.repository;

import com.davy.restapi.address.entity.Address;
import com.davy.restapi.shared.repository.GenericCrudRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class CustomAddressRepositoryImpl extends GenericCrudRepositoryImpl<Address>
        implements CustomAddressRepository {

    public CustomAddressRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Address.class);
    }
}