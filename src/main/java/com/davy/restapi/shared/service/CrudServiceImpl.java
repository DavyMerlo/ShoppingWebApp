package com.davy.restapi.shared.service;

import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CrudServiceImpl<T,C> implements CrudService<T,C> {

    protected final CrudRepository<T> repository;
    protected final ObjectMapper<C,T> mapper;

    @Override
    public Object findAll() {
        List<T> entities = repository.getAll();
        return entities
                .stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Object findById(Long id) {
        Optional<T> entityOptional = repository.getById(id);
        if (entityOptional.isEmpty()) {
            ThrowException.objectByIdException(id, "Object with " + id + " not found");
        }
        T entity = entityOptional.get();
        return mapper.mapToDetailsDto(entity);
    }

    @Override
    public Object save(C createRequest) {
        var entity = mapper.mapToEntity(createRequest);
        var savedEntity = repository.save((T) entity);
        return  savedEntity
                .stream()
                .map(mapper::mapToDetailsDto)
                .findFirst()
                .get();
    }

    @Override
    public void updateById(Long id, C createRequest){
        Optional<T> entityOptional = repository.getById(id);
        if (entityOptional.isEmpty()) {
            ThrowException.objectByIdException(id, "Object with " + id + " not found");
        }
        var updated = mapper.mapSourceToDestination(createRequest, (T) entityOptional.get());
        repository.update(updated);
    }
}
