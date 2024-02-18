package com.davy.restapi.shared.service;

public interface CrudService<T,C> {

    Object findAll();
    Object findById(Long id);
    Object save(C request);
    void updateById(Long id, C request);
}
