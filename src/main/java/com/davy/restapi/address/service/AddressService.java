package com.davy.restapi.address.service;

import com.davy.restapi.address.request.AddressUpdateRequest;
import com.davy.restapi.address.request.AddressCreateRequest;
import com.davy.restapi.address.response.AddressListResponse;
import com.davy.restapi.address.response.AddressResponse;

public interface AddressService {
    AddressListResponse findAllAddresses();
    AddressResponse findAddressById(Long id);
    Long saveAddress(AddressCreateRequest request);
    void updateAddressById(Long id, AddressUpdateRequest request);
}
