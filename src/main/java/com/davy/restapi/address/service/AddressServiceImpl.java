package com.davy.restapi.address.service;

import com.davy.restapi.address.entity.AddressEntity;
import com.davy.restapi.address.dto.AddressRequestDTO;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends CrudServiceImpl<AddressEntity, AddressRequestDTO>
implements AddressService{

    public AddressServiceImpl(CrudRepository<AddressEntity> repository,
                              ObjectMapper<AddressRequestDTO, AddressEntity> mapper) {
        super(repository, mapper);
    }
}
