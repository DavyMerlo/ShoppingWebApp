package com.davy.restapi.shared.mapper;


import com.davy.restapi.orderlines.entity.OrderLineEntity;
import org.springframework.stereotype.Service;

@Service
public interface ObjectMapper<S, D> {
    Object mapSourceToDestination(S source, D destination);

    Object mapToDto(D entity);

    Object mapToDetailsDto(D entity);

    D mapToEntity(S request);

    Object mapToListDto(D entity);
}

