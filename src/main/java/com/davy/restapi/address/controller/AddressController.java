package com.davy.restapi.address.controller;

import com.davy.restapi.address.request.AddressRequest;
import com.davy.restapi.address.service.AddressService;
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
        var data = addressService.findById(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @GetMapping
    public ResponseEntity<?> findAllAddresses(){
        var data = addressService.findAll();
        var response = ResponseHandler.generateResponse(true, HttpStatus.OK, data);
        System.out.println("Response to client: " + response);
        return response;
    }

    @PostMapping
    public ResponseEntity<?> saveAddress(@RequestBody AddressRequest request){
        var data = addressService.save(request);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddressById(@PathVariable(value = "id") final Long id,
                                               @RequestBody AddressRequest request){
        addressService.updateById(id, request);
        var data = addressService.findById(id);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }
}
