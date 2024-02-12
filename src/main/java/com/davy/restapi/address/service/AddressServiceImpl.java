package com.davy.restapi.address.service;

import com.davy.restapi.address.dto.AddressDetail;
import com.davy.restapi.address.dto.AddressDto;
import com.davy.restapi.address.entity.Address;
import com.davy.restapi.address.request.AddressRequest;
import com.davy.restapi.address.response.AddressListResponse;
import com.davy.restapi.address.response.AddressResponse;
import com.davy.restapi.shared.mapper.ResponseMapper;
import com.davy.restapi.shared.repository.GenericCrudRepository;
import com.davy.restapi.shared.service.GenericCrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl extends GenericCrudServiceImpl<Address, AddressRequest>
    implements AddressService{

    public AddressServiceImpl(GenericCrudRepository<Address> repository,
                              ResponseMapper<AddressRequest, Address> responseMapper) {
        super(repository, responseMapper);
    }

    @Override
    public List<AddressDto> findAll() {
        return (List<AddressDto>) super.findAll();
    }

    @Override
    public AddressDetail findById(Long id) {
        return (AddressDetail) super.findById(id);
    }

    @Override
    public AddressDetail save(AddressRequest createRequest) {
        return (AddressDetail) super.save(createRequest);
    }

    @Override
    public void updateById(Long id, AddressRequest request) {
        super.updateById(id, request);
    }
}
