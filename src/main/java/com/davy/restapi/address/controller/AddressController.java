package com.davy.restapi.address.controller;

import com.davy.restapi.address.dto.AddressDetail;
import com.davy.restapi.address.dto.AddressDto;
import com.davy.restapi.address.entity.Address;
import com.davy.restapi.address.dto.AddressRequest;
import com.davy.restapi.address.response.AddressListResponse;
import com.davy.restapi.address.response.AddressResponse;
import com.davy.restapi.shared.handler.ResponseHandler;
import com.davy.restapi.shared.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final CrudService<Address, AddressRequest> addressService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findAddressById(@PathVariable(value = "id") final Long id){
        var response = new AddressResponse();
        var address = addressService.findById(id);
        response.setAddress((AddressDetail) address);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, response);
    }

    @GetMapping
    public ResponseEntity<?> findAllAddresses(){
        var response = new AddressListResponse();
        var addresses = addressService.findAll();
        response.setAddresses((List<AddressDto>) addresses);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, response);
    }

    @PostMapping
    public ResponseEntity<?> saveAddress(@RequestBody AddressRequest request){
        var response = new AddressResponse();
        var address = addressService.save(request);
        response.setAddress((AddressDetail) address);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddressById(@PathVariable(value = "id") final Long id,
                                               @RequestBody AddressRequest request){
        var response = new AddressResponse();
        addressService.updateById(id, request);
        var address = addressService.findById(id);
        response.setAddress((AddressDetail) address);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, response);
    }
}
