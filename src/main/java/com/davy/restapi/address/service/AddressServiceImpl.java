package com.davy.restapi.address.service;

import com.davy.restapi.address.request.AddressUpdateRequest;
import com.davy.restapi.address.entity.Address;
import com.davy.restapi.address.mapper.AddressItemsMapper;
import com.davy.restapi.address.repository.AddressRepository;
import com.davy.restapi.address.request.AddressCreateRequest;
import com.davy.restapi.address.response.AddressListResponse;
import com.davy.restapi.address.response.AddressResponse;
import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.shared.validators.RequestValidator;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressItemsMapper addressItemsMapper;
    private final RequestValidator<AddressCreateRequest> addressCreateRequestValidator;
    private final RequestValidator<AddressUpdateRequest> addressUpdateRequestValidator;

    @Override
    public AddressListResponse findAllAddresses() {
        AddressListResponse response = new AddressListResponse();
        if(addressRepository.getAllAddresses().isEmpty()){
            ThrowException.objectException("Addresses");
        }
        response.addresses = addressRepository.getAllAddresses()
                .stream()
                .map(addressItemsMapper)
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public AddressResponse findAddressById(Long id) {
        AddressResponse response = new AddressResponse();
        if(addressRepository.getAddressById(id).isEmpty()){
            ThrowException.objectByIdException(id, "Address");
        }
        response.address = addressRepository.getAddressById(id)
                .stream()
                .map(addressItemsMapper)
                .findFirst()
                .get();
        return response;
    }

    @Override
    public AddressResponse saveAddress(AddressCreateRequest request) {
        addressCreateRequestValidator.validate(request);
        var address = Address.builder()
                .street(request.getStreet())
                .houseNumber(request.getHouseNumber())
                .busNumber(request.getBusNumber())
                .localAuthority(request.getLocalAuthority())
                .postalCode(request.getPostalCode())
                .build();
        addressRepository.save(address);
        return this.findAddressById(address.getId());
    }

    @Override
    public AddressResponse updateAddressById(Long id, AddressUpdateRequest request) {
        addressUpdateRequestValidator.validate(request);
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
