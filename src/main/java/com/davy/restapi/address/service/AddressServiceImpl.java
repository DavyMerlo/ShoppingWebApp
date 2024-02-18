package com.davy.restapi.address.service;

import com.davy.restapi.address.entity.Address;
import com.davy.restapi.address.dto.AddressRequest;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.CrudRepository;
import com.davy.restapi.shared.service.CrudServiceImpl;
import org.springframework.stereotype.Service;
@Service

public class AddressServiceImpl extends CrudServiceImpl<Address, AddressRequest>
implements AddressService{

    public AddressServiceImpl(CrudRepository<Address> repository,
                              ObjectMapper<AddressRequest, Address> mapper) {
        super(repository, mapper);
    }
}
