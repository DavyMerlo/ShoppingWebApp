package com.davy.restapi.card.repository;

import com.davy.restapi.card.entity.CustomerCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CustomerCardEntity, Long>,
        CustomCardRepository{
}