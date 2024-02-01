package com.davy.restapi.address.service;

import com.davy.restapi.address.request.AddressRequest;
import com.davy.restapi.address.response.AddressListResponse;
import com.davy.restapi.address.response.AddressResponse;

public interface AddressService {
      AddressListResponse findAll();
      AddressResponse findById(Long id);
      AddressResponse save(AddressRequest request);
      void updateById(Long id, AddressRequest request);
}
