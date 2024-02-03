package com.davy.restapi.shared.mapper;

import org.springframework.stereotype.Service;

@Service
public interface ResponseMapper<S, D> {
    Object mapSourceToDestination(S source, D destination);

    Object mapToDto(D entity);

    Object mapToDetails(D entity);

    Object mapToEntity(S request);
}

