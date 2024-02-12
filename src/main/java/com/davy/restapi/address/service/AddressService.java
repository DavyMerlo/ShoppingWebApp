package com.davy.restapi.address.service;

import com.davy.restapi.address.dto.AddressDetail;
import com.davy.restapi.address.dto.AddressDto;
import com.davy.restapi.address.request.AddressRequest;

import java.util.List;


public interface AddressService {
      List<AddressDto> findAll();
      AddressDetail findById(Long id);
      AddressDetail save(AddressRequest request);
      void updateById(Long id, AddressRequest request);
}
