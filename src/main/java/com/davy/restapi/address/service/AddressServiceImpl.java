package com.davy.restapi.address.service;

import com.davy.restapi.address.dto.AddressDetail;
import com.davy.restapi.address.dto.AddressDto;
import com.davy.restapi.address.entity.Address;
import com.davy.restapi.address.request.AddressRequest;
import com.davy.restapi.address.response.AddressListResponse;
import com.davy.restapi.address.response.AddressResponse;
import com.davy.restapi.shared.mapper.ObjectMapper;
import com.davy.restapi.shared.repository.AbstractCrudRepository;
import com.davy.restapi.shared.service.AbstractCrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl extends AbstractCrudService<Address, AddressRequest>
        implements AddressService {

    public AddressServiceImpl(AbstractCrudRepository<Address> repository,
                              ObjectMapper<AddressRequest, Address> objectMapper) {
        super(repository, objectMapper);
    }

    @Override
    public AddressListResponse findAll() {
        var response = new AddressListResponse();
        var addresses = super.findAll();
        response.setAddresses((List<AddressDto>) addresses);
        System.out.println(response.getAddresses());
        return response;
    }

    @Override
    public AddressResponse findById(Long id) {
        var response = new AddressResponse();
        var address = super.findById(id);
        response.setAddress((AddressDetail) address);
        return response;
    }

    @Override
    public AddressResponse save(AddressRequest createRequest) {
        var response = new AddressResponse();
        var address = super.save(createRequest);
        response.setAddress((AddressDetail) address);
        return response;
    }

    @Override
    public void updateById(Long id, AddressRequest request) {
        super.updateById(id, request);
    }
}
