package com.davy.restapi.address.repository;

import com.davy.restapi.address.entity.Address;
import com.davy.restapi.shared.repository.CrudRepository;
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
public class CustomAddressRepositoryImpl implements CrudRepository<Address> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Address> getAll() {
        Query query = entityManager.createQuery("SELECT A from Address A");
        return query.getResultList();
    }

    @Override
    public Optional<Address> getById(Long id) {
        return Optional.ofNullable(entityManager.find(Address.class, id));
    }

    @Override
    @Transactional
    public Optional<Address> save(Object entity) {
        entityManager.persist(entity);
        entityManager.flush();
        if (entity instanceof Address) {
            return Optional.of((Address) entity);
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void update(Object entity) {
        entityManager.merge(entity);
    }
}
