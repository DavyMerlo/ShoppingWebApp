package com.davy.restapi.shared.repository;

import com.davy.restapi.address.repository.CustomAddressRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrudRepository<T> extends CustomAddressRepository {

    List<T> getAll();

    Optional<T> getById(Long id);

    Optional<T> save(Object entity);

    void update(Object entity);
//
//    void delete(Long id);
}
