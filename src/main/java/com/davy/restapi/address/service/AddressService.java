package com.davy.restapi.address.service;

import com.davy.restapi.address.dto.AddressRequestDTO;
import com.davy.restapi.address.entity.Address;
import com.davy.restapi.shared.service.CrudService;

public interface AddressService extends CrudService<Address, AddressRequestDTO> {
}
