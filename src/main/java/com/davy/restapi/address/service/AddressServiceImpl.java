package com.davy.restapi.address.service;

import com.davy.restapi.address.entity.Address;
import com.davy.restapi.address.dto.AddressRequestDTO;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends CrudServiceImpl<Address, AddressRequestDTO>
implements AddressService{

    public AddressServiceImpl(CrudRepository<Address> repository,
                              ObjectMapper<AddressRequestDTO, Address> mapper) {
        super(repository, mapper);
    }
}
