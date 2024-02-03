package com.davy.restapi.card.repository;

import com.davy.restapi.address.repository.CustomAddressRepository;
import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.shared.repository.AbstractCrudRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class CustomCardRepositoryImpl extends AbstractCrudRepositoryImpl<CustomerCard>
    implements CustomAddressRepository {

    public CustomCardRepositoryImpl(EntityManager entityManager) {
        super(entityManager, CustomerCard.class);
    }
}
