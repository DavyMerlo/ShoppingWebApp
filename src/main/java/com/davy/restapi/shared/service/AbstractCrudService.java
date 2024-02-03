package com.davy.restapi.shared.service;

import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.shared.mapper.ResponseMapper;
import com.davy.restapi.shared.repository.AbstractCrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public abstract class AbstractCrudService<T, C> {

    protected final AbstractCrudRepository<T> repository;
    protected final ResponseMapper<C,T> responseMapper;

    public Object findAll() {
        List<T> entities = repository.getAll();
        return entities
                .stream()
                .map(responseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Object findById(Long id) {
        Optional<T> entityOptional = repository.getById(id);
        if (entityOptional.isEmpty()) {
            ThrowException.objectByIdException(id, "Object with " + id + " not found");
        }
        T entity = entityOptional.get();
        return responseMapper.mapToDetails(entity);
    }

    public Object save(C createRequest) {
        var entity = responseMapper.mapToEntity(createRequest);
        var savedEntity = repository.save(entity);
        return  savedEntity
                .stream()
                .map(responseMapper::mapToDto)
                .findFirst()
                .get();
    }

    public void updateById(Long id, C createRequest){
        var existingEntity = repository.getById(id);
        if (existingEntity.isEmpty()) {
            ThrowException.objectByIdException(id, "Object with " + id + " not found");
        }
        var updated = responseMapper.mapSourceToDestination(createRequest, (T) existingEntity.get());
        repository.update(updated);
    }
}
