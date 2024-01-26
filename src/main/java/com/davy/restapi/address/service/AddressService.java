package com.davy.restapi.address.service;

import com.davy.restapi.address.request.AddressUpdateRequest;
import com.davy.restapi.address.request.AddressCreateRequest;
import com.davy.restapi.address.response.AddressListResponse;
import com.davy.restapi.address.response.AddressResponse;

public interface AddressService {
    AddressListResponse findAllAddresses();
    AddressResponse findAddressById(Long id);
    AddressResponse saveAddress(AddressCreateRequest request);
    AddressResponse updateAddressById(Long id, AddressUpdateRequest request);
}
