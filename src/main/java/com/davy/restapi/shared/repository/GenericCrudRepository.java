package com.davy.restapi.shared.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Repository
public interface GenericCrudRepository<T> {

    List<T> getAll();

    Optional<T> getById(Long id);

    Optional<T> save(T entity);

    void update(Object entity);
}
