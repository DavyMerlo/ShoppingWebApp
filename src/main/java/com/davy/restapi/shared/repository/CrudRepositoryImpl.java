package com.davy.restapi.shared.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CrudRepositoryImpl<T> implements CrudRepository<T> {

    @PersistenceContext
    private final EntityManager entityManager;
    private final Class<T> entity;

    @Override
    public List<T> getAll() {
        System.out.println(entity);
        Query query = entityManager
                .createQuery("SELECT entity FROM " + entity.getSimpleName() + " entity");
        return query.getResultList();
    }

    @Override
    public Optional<T> getById(Long id) {
        return Optional.ofNullable(entityManager.find(entity, id));
    }

    @Transactional
    @Override
    public Optional<T> save(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return Optional.ofNullable(entity);
    }

    @Transactional
    @Override
    public void update(Object entity) {
        entityManager.merge(entity);
    }
}
