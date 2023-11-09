package com.davy.restapi.address.service;

import com.davy.restapi.address.request.AddressUpdateRequest;
import com.davy.restapi.address.request.AddressCreateRequest;
import com.davy.restapi.address.response.AddressListResponse;

public interface AddressService {
    AddressListResponse findAllAddresses();
    Object findAddressById(Long id);
    Object saveAddress(AddressCreateRequest request);
    Object updateAddressById(Long id, AddressUpdateRequest request);
}
