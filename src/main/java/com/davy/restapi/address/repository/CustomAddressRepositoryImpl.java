package com.davy.restapi.address.repository;

import com.davy.restapi.address.entity.Address;
import com.davy.restapi.shared.repository.AbstractCrudRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class CustomAddressRepositoryImpl extends AbstractCrudRepositoryImpl<Address>
        implements CustomAddressRepository {

    public CustomAddressRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Address.class);
    }
}
