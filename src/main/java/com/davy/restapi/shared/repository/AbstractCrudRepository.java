package com.davy.restapi.shared.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AbstractCrudRepository<T> {

    List<T> getAll();

    Optional<T> getById(Long id);

    Optional<T> save(Object entity);

    void update(Object entity);
}
