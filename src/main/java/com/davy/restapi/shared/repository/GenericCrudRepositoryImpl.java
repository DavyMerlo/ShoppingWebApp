package com.davy.restapi.shared.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class GenericCrudRepositoryImpl<T> implements GenericCrudRepository<T>{

    @PersistenceContext
    private final EntityManager entityManager;
    private final Class<T> entityType;

    public List<T> getAll() {
        System.out.println(entityType);
        Query query = entityManager.createQuery("SELECT entity FROM " +
                entityType.getSimpleName() + " entity");
        return query.getResultList();
    }

    public Optional<T> getById(Long id) {
        return Optional.ofNullable(entityManager.find(entityType, id));
    }

    @Transactional
    public Optional<T> save(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return Optional.ofNullable(entity);
    }

    @Transactional
    public void update(Object entity) {
        entityManager.merge(entity);
    }
}
