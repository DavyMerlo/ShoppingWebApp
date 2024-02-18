package com.davy.restapi.shared.service;

import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public abstract class CrudServiceImpl<T,C> implements CrudService<T,C> {

    protected final CrudRepository<T> repository;
    protected final ObjectMapper<C,T> mapper;

    public Object findAll() {
        List<T> entities = repository.getAll();
        return entities
                .stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Object findById(Long id) {
        Optional<T> entityOptional = repository.getById(id);
        if (entityOptional.isEmpty()) {
            ThrowException.objectByIdException(id, "Object with " + id + " not found");
        }
        T entity = entityOptional.get();
        return mapper.mapToDetails(entity);
    }

    public Object save(C createRequest) {
        var entity = mapper.mapToEntity(createRequest);
        var savedEntity = repository.save((T) entity);
        return  savedEntity
                .stream()
                .map(mapper::mapToDetails)
                .findFirst()
                .get();
    }

    public void updateById(Long id, C createRequest){
        var existingEntity = repository.getById(id);
        if (existingEntity.isEmpty()) {
            ThrowException.objectByIdException(id, "Object with " + id + " not found");
        }
        var updated = mapper.mapSourceToDestination(createRequest, (T) existingEntity.get());
        repository.update(updated);
    }
}
