package com.davy.restapi.address.repository;

import com.davy.restapi.address.entity.Address;
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
public class CustomAddressRepositoryImpl implements CustomAddressRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Address> getAllAddresses() {
        Query query = entityManager.createQuery("SELECT A from Address A");
        return query.getResultList();
    }

    @Override
    public Optional<Address> getAddressById(Long id) {
        return Optional.ofNullable(entityManager.find(Address.class, id));
    }

    @Override
    @Transactional
    public Long saveAddress(Address address) {
        entityManager.persist(address);
        entityManager.flush();
        return address.getId();
    }

    @Override
    @Transactional
    public void updateAddress(Address address) {
        entityManager.merge(address);
    }

    @Override
    public void removeAddress(Address address) {

    }
}
