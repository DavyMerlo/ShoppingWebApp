package com.davy.restapi.card.repository;

import com.davy.restapi.card.entity.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CustomerCard, Long>,
        CustomCardRepository{
}
