package com.davy.restapi.card.repository;

import com.davy.restapi.card.entity.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.smartcardio.Card;
import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<CustomerCard, Long>,
        CustomCardRepository{
}
