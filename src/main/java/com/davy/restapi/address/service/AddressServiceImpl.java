package com.davy.restapi.address.service;

import com.davy.restapi.address.request.AddressUpdateRequest;
import com.davy.restapi.address.entity.Address;
import com.davy.restapi.address.mapper.AddressMapper;
import com.davy.restapi.address.repository.AddressRepository;
import com.davy.restapi.address.request.AddressCreateRequest;
import com.davy.restapi.address.response.AddressListResponse;
import com.davy.restapi.address.response.AddressResponse;
import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.shared.validators.RequestValidatorImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final RequestValidatorImpl<AddressCreateRequest> addressCreateRequestValidatorImpl;
    private final RequestValidatorImpl<AddressUpdateRequest> addressUpdateRequestValidatorImpl;

    @Override
    public AddressListResponse findAllAddresses() {
        AddressListResponse response = new AddressListResponse();
        if(addressRepository.getAllAddresses().isEmpty()){
            ThrowException.objectException("Addresses");
        }
        response.setAddresses(addressRepository.getAllAddresses()
                .stream()
                .map(addressMapper)
                .collect(Collectors.toList()));
        return response;
    }

    @Override
    public AddressResponse findAddressById(Long id) {
        AddressResponse response = new AddressResponse();
        if(addressRepository.getAddressById(id).isEmpty()){
            ThrowException.objectByIdException(id, "Address");
        }
        response.setAddress(addressRepository.getAddressById(id)
                .stream()
                .map(addressMapper)
                .findFirst()
                .get());
        return response;
    }

    @Override
    public AddressResponse saveAddress(AddressCreateRequest request) {
        addressCreateRequestValidatorImpl.validate(request);
        var address = Address.builder()
                .street(request.getStreet())
                .houseNumber(request.getHouseNumber())
                .busNumber(request.getBusNumber())
                .localAuthority(request.getLocalAuthority())
                .postalCode(request.getPostalCode())
                .build();
        addressRepository.saveAddress(address);
        return this.findAddressById(address.getId());
    }

    @Override
    public AddressResponse updateAddressById(Long id, AddressUpdateRequest request) {
        addressUpdateRequestValidatorImpl.validate(request);
        var address = addressRepository.getAddressById(id);
        if(address.isEmpty()){
            ThrowException.objectByIdException(id, "Address");
        }
        address.get().setStreet(request.getStreet());
        address.get().setHouseNumber(request.getHouseNumber());
        address.get().setBusNumber(request.getBusNumber());
        address.get().setPostalCode(request.getPostalCode());
        address.get().setLocalAuthority(request.getLocalAuthority());
        addressRepository.updateAddress(address.get());
        return this.findAddressById(address.get().getId());
    }
}
