package com.davy.restapi.shared.repository;

import java.util.List;
import java.util.Optional;


public interface CrudRepository<T> {

    List<T> getAll();

    Optional<T> getById(Long id);

    Optional<T> save(T entity);

    void update(Object entity);
}
