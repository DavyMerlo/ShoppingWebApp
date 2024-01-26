package com.davy.restapi.card.repository;

import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.card.response.CardResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomCardRepositoryImpl implements CustomCardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerCard> getAllCustomerCards() {
        Query query = entityManager.createQuery("SELECT CC from CustomerCard CC");
        return query.getResultList();
    }

    @Override
    public Optional<CustomerCard> getCustomerCardById(Long id) {
        return Optional.ofNullable(entityManager.find(CustomerCard.class, id));
    }

    @Override
    @Transactional
    public void saveCustomerCard(CustomerCard customerCard) {
        entityManager.persist(customerCard);
    }

    @Override
    @Transactional
    public void updateCustomerCard(CustomerCard customerCard) {
        entityManager.merge(customerCard);
    }

    @Override
    public void removeCustomer(CustomerCard customerCard) {

    }
}
