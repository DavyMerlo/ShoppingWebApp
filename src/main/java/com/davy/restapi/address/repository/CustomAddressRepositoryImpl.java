package com.davy.restapi.address.repository;

import com.davy.restapi.address.entity.AddressEntity;
import com.davy.restapi.shared.repository.CrudRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class CustomAddressRepositoryImpl extends CrudRepositoryImpl<AddressEntity>
        implements CustomAddressRepository {

    public CustomAddressRepositoryImpl(EntityManager entityManager) {
        super(entityManager, AddressEntity.class);
    }
}