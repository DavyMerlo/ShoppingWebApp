package com.davy.restapi.address.controller;

import com.davy.restapi.address.request.AddressCreateRequest;
import com.davy.restapi.address.service.AddressService;
import com.davy.restapi.address.request.AddressUpdateRequest;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findAddressById(@PathVariable(value = "id") final Long id){
        var data = addressService.findAddressById(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @GetMapping
    public ResponseEntity<?> findAllAddresses(){
        var data = addressService.findAllAddresses();
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @PostMapping
    public ResponseEntity<?> saveAddress(@RequestBody AddressCreateRequest request){
        var data = addressService.saveAddress(request);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddressById(@PathVariable(value = "id") final Long id,
                                               @RequestBody AddressUpdateRequest request){
        addressService.updateAddressById(id, request);
        var data = addressService.findAddressById(id);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }
}
