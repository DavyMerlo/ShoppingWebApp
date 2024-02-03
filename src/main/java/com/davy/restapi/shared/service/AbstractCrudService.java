package com.davy.restapi.shared.service;

import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.shared.mapper.ObjectMapper;
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
    protected final ObjectMapper<C,T> objectMapper;

    public Object findAll() {
        List<T> entities = repository.getAll();
        return entities
                .stream()
                .map(objectMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Object findById(Long id) {
        Optional<T> entityOptional = repository.getById(id);
        if (entityOptional.isEmpty()) {
            ThrowException.objectByIdException(id, "Object with " + id + " not found");
        }
        T entity = entityOptional.get();
        return objectMapper.mapToDetails(entity);
    }

    public Object save(C createRequest) {
        var entity = objectMapper.mapToEntity(createRequest);
        var savedEntity = repository.save(entity);
        return  savedEntity
                .stream()
                .map(objectMapper::mapToDto)
                .findFirst()
                .get();
    }

    public void updateById(Long id, C createRequest){
        var existingEntity = repository.getById(id);
        if (existingEntity.isEmpty()) {
            ThrowException.objectByIdException(id, "Object with " + id + " not found");
        }
        var updated = objectMapper.mapSourceToDestination(createRequest, (T) existingEntity.get());
        repository.update(updated);
    }
}
